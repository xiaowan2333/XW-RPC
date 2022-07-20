package version2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 19:57
 */
public class ThreadPoolRpcServer implements RpcServer{
    //还是一样存着可用服务
    private Map<String, Object> serviceProvide;
    //加了一个线程池
    private final ThreadPoolExecutor threadPool;

    //默认池子大小
    public ThreadPoolRpcServer(Map<String, Object> serviceProvide){
        this.serviceProvide = serviceProvide;
        threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000,600, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));
    }
    //自定义池子参数构造
    public ThreadPoolRpcServer(Map<String, Object> serviceProvide, int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue){
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.serviceProvide = serviceProvide;
    }
    @Override
    public void start(int port) {
        System.out.println("服务端启动了");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                threadPool.execute(new WorkThread(socket,serviceProvide));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void top() {

    }
}
