package version1.client;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:45
 */

import version1.common.RpcRequest;
import version1.common.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * socket通信接口
 */
public class IOClient {
    public static RpcResponse sentRequest(String host,int port,RpcRequest request){
        System.out.println("socket通信接口启动");
        try {
            Socket socket = new Socket(host,port);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("客户端发送调用请求"+request);

            oos.writeObject(request);
            oos.flush();

            System.out.println("客户端接受返回值");
            RpcResponse re = (RpcResponse) ois.readObject();
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
