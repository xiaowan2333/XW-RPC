package version2.server;

import version2.service.OrderService;
import version2.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 19:48
 */
public class RpcServeStarter {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Map<String,Object> serviceProvide = new HashMap<>();
        //对外暴露接口服务（一个实现类，可能有多个要调用的接口，这里直接写死不太严谨）
        //但是我这里只实现了一个要用的接口，后续升级可以通过注解暴露，扫描添加到容器
        serviceProvide.put(UserService.class.getName(),userService);
        serviceProvide.put(OrderService.class.getName(),orderService);
//        serviceProvide.forEach((k,v)->{
////            System.out.println(k+"::"+v);
////        });
        //serviceProvide给到RpcServer,并启动server
        SimpleRpcServer simpleRpcServer = new SimpleRpcServer(serviceProvide);
        simpleRpcServer.start(8899);

    }
}
