package version2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 17:13
 */
public class SimpleRpcServer implements RpcServer{
    // 存着服务接口名-> service对象的map
    private Map<String, Object> serviceProvide;

    public SimpleRpcServer(Map<String,Object> serviceProvide){
        this.serviceProvide = serviceProvide;
    }

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
