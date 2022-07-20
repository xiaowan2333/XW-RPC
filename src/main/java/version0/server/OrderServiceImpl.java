package version0.server;

import version0.common.Order;
import version0.common.OrderService;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 13:27
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
}
