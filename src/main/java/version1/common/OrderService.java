package version1.common;

import version1.common.Order;

/**
 * @Author: XiaoWan
 * @Date: 2022/7/20 13:26
 */
public interface OrderService {

    Order getOrderById(Integer id);

    Integer insertOrder(Order order);
}
