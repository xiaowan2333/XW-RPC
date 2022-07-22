package version5.server;

import version5.common.RpcRequest;
import version5.common.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Objects;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 17:16
 */
public class WorkThread implements Runnable {
    private Socket socket;
    private ServiceProvide serviceProvide;
    public WorkThread(Socket socket, ServiceProvide serviceProvide) {
        this.serviceProvide = serviceProvide;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //接受来自客户端的消息
            RpcRequest request = (RpcRequest) ois.readObject();
            System.out.println("接收到客户端消息" + request);

            //判断应该反射调用的接口,并调用(功能已拆离)
            RpcResponse response = getResponse(request);

            System.out.println("即将发送返回值");
            //写入客户端
            oos.writeObject(response);
            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("socket连接io异常");
        }
    }

    //
    private RpcResponse getResponse(RpcRequest request) {
        //先获取到服务名
        String interfaceName = request.getInterfaceName();
        //通过服务接口名获取到对应实现对象
        Object service = serviceProvide.getService(interfaceName);
        if (!Objects.isNull(service)){
            try {
                //反射获取服务方法
                Method method = service.getClass().
                        getMethod(request.getMethodName(), request.getParamTypes());
                //调用服务方法
                Object invoke = method.invoke(service, request.getParams());
                System.out.println("服务调用返回值："+invoke);
                return RpcResponse.success(invoke);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("方法执行错误");
                return RpcResponse.fail();
            }
        }

        return null;
    }
}
