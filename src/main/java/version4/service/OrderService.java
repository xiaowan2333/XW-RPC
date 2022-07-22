package version4.service;

import version4.common.Order;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 16:05
 */
public interface OrderService {
    Order getOrderById(Integer id);
    Integer insertOrder(Order order);
}
