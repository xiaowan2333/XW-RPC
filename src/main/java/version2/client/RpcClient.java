package version2.client;

import version2.common.Order;
import version2.common.User;
import version2.service.OrderService;
import version2.service.UserService;

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
        System.out.println("[over]远程调用第一个服务第一个方法返回值"+orderById);

        //调用服务的另一个方法
        Integer re = proxy.insertOrder(Order.builder().proid(3377l).username("xiaowan").build());
        System.out.println("[over1]远程调用第二个方法的返回值"+re);

        //调用user服务的一个方法
        UserService proxy1 = clientProxy.getProxy(UserService.class);
        User one = proxy1.getUserByName("xiaowan");
        System.out.println("[over2]远程调用userService接口方法成功！返回值为"+one);
    }
}
