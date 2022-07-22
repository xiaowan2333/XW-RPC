package version5.server;

import version5.server.impl.OrderServiceImpl;
import version5.server.impl.UserServiceImpl;
import version5.service.OrderService;
import version5.service.UserService;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 14:47
 */

public class TestRpcServer {
    public static void main(String[] args) throws InterruptedException {
//        初始化一个服务列表
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        ServiceProvide serviceProvide = new ServiceProvide("127.0.0.1",9990);
        serviceProvide.provideServiceInterface(userService);
        serviceProvide.provideServiceInterface(orderService);

        //再开一个线程
        new Thread(()->{
            //        启动一个netty的RPCServer,端口为9990
            RpcServer server2 = new NettyRpcServer(serviceProvide);
            server2.start(9990);
        }).start();

//        先启动一个simpleRpcServer
//        RpcServer server1 = new SimpleRpcServer(serviceProvide);
//        server1.start(8899);

    }
}
