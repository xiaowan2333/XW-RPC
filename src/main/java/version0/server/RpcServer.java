package version0.server;

import version0.common.Order;
import version0.common.OrderService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 13:46
 */
public class RpcServer {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        try {
            ServerSocket socket = new ServerSocket(8899);
            System.out.println("服务器启动了！");
            while (true) {
                Socket accept = socket.accept();
                //接受一个socket请求就开一个线程
                new Thread(() -> {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
                        //读取客户端传过来的对象
                        Integer i = (Integer) objectInputStream.readObject();
                        System.out.println("获取到客户端传来的对象" + i);
                        Order orderById = orderService.getOrderById(i);
                        System.out.println("查询到值--即将返回");
                        objectOutputStream.writeObject(orderById);
                        objectOutputStream.flush();

                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("IO中读取对象错误！");
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println("服务器启动失败！");
            e.printStackTrace();
        }
    }
}
