package version5.server.impl;

import version5.common.Order;
import version5.service.OrderService;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 14:25
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrderById(Integer id) {
        //模拟从数据库中取值过程
        Order or = new Order();
        or.setProname("大保健！！");
        or.setId(Long.valueOf(id));
        return or;
    }

    @Override
    public Integer insertOrder(Order order) {
        System.out.println("插入数据成功！"+order);
        return 1;
    }
}
