package version5.client;

import lombok.AllArgsConstructor;
import version5.common.RpcRequest;
import version5.common.RpcResponse;
import version5.register.NacosRegister;
import version5.register.ServerRegister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:17
 */
public class SimpleRpcClient implements RpcClient {

    private String host;
    private int port;
    private ServerRegister register;
    public SimpleRpcClient(){
        register = new NacosRegister();
    }

    @Override
    public RpcResponse sentRequest(RpcRequest request) {
        System.out.println("socket通信接口启动--");
        InetSocketAddress inetSocketAddress = register.serviceDiscovery(request.getInterfaceName());
        if (inetSocketAddress==null){
            System.out.println("找不到可用服务！");
            return new RpcResponse().fail();
        }
        host = inetSocketAddress.getHostName();
        port = inetSocketAddress.getPort();
        try {
            Socket socket = new Socket(host,port);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("客户端发送调用请求"+request);

            oos.writeObject(request);
            oos.flush();
            RpcResponse re = (RpcResponse) ois.readObject();
            System.out.println("客户端接受返回值为："+re);
            return re;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return RpcResponse.fail();
    }
}
