package version3.client;

import lombok.AllArgsConstructor;
import version3.common.RpcRequest;
import version3.common.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:17
 */
@AllArgsConstructor
public class SimpleRpcClient implements RpcClient{

    private String host;
    private int port;

    @Override
    public RpcResponse sentRequest(RpcRequest request) {
        System.out.println("socket通信接口启动--");
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
