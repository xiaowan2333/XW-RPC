package version1.client;

import version1.common.Order;
import version1.common.OrderService;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 15:16
 */
public class RpcClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        OrderService proxy = clientProxy.getProxy(OrderService.class);
        //调用服务的一个方法
        Order orderById = proxy.getOrderById(3);
        System.out.println(orderById);

        //调用服务的另一个方法
        Integer re = proxy.insertOrder(Order.builder().proid(3377l).username("xiaowan").build());
        System.out.println("远程调用第二个方法的返回值"+re);
    }
}
