package version0.client;

import version0.common.Order;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 13:35
 */
public class RpcClient {
    public static void main(String[] args) {
        try {
            System.out.println("客户端启动--发送请求");
            //建立socket连接
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            //给服务器发送请求对象
            objectOutputStream.writeObject(new Integer(new Random().nextInt()));
            objectOutputStream.flush();

            Order order = (Order) objectInputStream.readObject();
            System.out.println("服务端返回的对象" + order);
        } catch (UnknownHostException e) {
            System.out.println("未知主机异常");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
