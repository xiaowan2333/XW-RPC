package version3.server;

import lombok.AllArgsConstructor;
import version3.server.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 14:39
 */
@AllArgsConstructor
public class SimpleRpcServer implements RpcServer{
    private ServiceProvide serviceProvide;
    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("simple服务端启动了");
            // BIO的方式监听Socket
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("获取到一个socket连接，开启新线程去处理");
                // 开启一个新线程去处理
                new Thread(new WorkThread(socket,serviceProvide)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    @Override
    public void top() {

    }
}
