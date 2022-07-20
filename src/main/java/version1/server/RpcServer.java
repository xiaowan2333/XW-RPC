package version1.server;

import version1.common.OrderService;
import version1.common.RpcRequest;
import version1.common.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:29
 */
public class RpcServer {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        try {
            ServerSocket socket = new ServerSocket(8899);
            System.out.println("服务端启动！");
            while (true) {
                Socket accept = socket.accept();
                new Thread(() -> {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(accept.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());
                        //接受来自客户端的消息
                        RpcRequest request = (RpcRequest) ois.readObject();
                        System.out.println("接收到客户端消息" + request);

                        //反射调用方法
                        Method method = orderService.getClass().getMethod(request.getMethodName(), request.getParamTypes());
                        Object invoke = method.invoke(orderService, request.getParams());

                        //给客户端回应返回统一结果
                        oos.writeObject(RpcResponse.success(invoke));
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        System.out.println("找不到这样参数的方法");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败？？");
        }
    }
}
