package version4.client;

import version4.common.Order;
import version4.common.User;
import version4.service.OrderService;
import version4.service.UserService;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/21 9:26
 */
public class TestClient {
    public static void main(String[] args) {
        //创建基于bio的socket客户端，并塞入代理
        SimpleRpcClient simpleRpcClient = new SimpleRpcClient("127.0.0.1", 8899);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(simpleRpcClient);

        OrderService orderService = rpcClientProxy.getProxy(OrderService.class);
        Order orderById = orderService.getOrderById(34);
        System.out.println("返回值是："+orderById);

        UserService userService = rpcClientProxy.getProxy(UserService.class);
        User user = userService.getUserByName("xiaowang");
        System.out.println("返回值是："+user);

        System.out.println("分割线-----------------------下面是netty网络请求");


        //创建netty客户端
        NettyRpcClient nettyRpcClient = new NettyRpcClient("127.0.0.1", 9990);
        RpcClientProxy nettyProxy = new RpcClientProxy(nettyRpcClient);

        UserService userService1 = nettyProxy.getProxy(UserService.class);
        User reUser = userService1.getUserByName("我是netty");
        System.out.println("返回值是："+reUser);

    }
}
