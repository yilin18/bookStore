package cn.bookStore.client.order.dao;

import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Orderitem;
import cn.bookStore.commons.beans.User;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/6
 */
public interface IOrderDao {

    int insertOrder(Order order);

    void insertOrderItem(Orderitem orderitem);

    void changeProductNum(Orderitem orderitem);

    Order selectOrderByOrderId(String id);

    void updateOrderStatus(String out_trade_no);

    List<Order> selectOrderByUser(Map map);

    int findOrderCountByUser(User user);

    void deleteOrderByOrderId(String id);

    void changeProductNumberByItem(Orderitem orderitem);
}
