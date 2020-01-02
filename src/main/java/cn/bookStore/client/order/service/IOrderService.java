package cn.bookStore.client.order.service;

import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Orderitem;
import cn.bookStore.commons.beans.User;
import cn.bookStore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/6
 */
public interface IOrderService {
    int creatOrder(Order order) throws Exception;

    Order findOrderByOrderId(String out_trade_no);

    void modifyOrderStatus(String out_trade_no);

    List<Order> findOrderByUser(PageModel pageModel, User user);

    int findOrderCountByUser(User user);

    void deleteOrderByOrderId(String id, String type, Orderitem orderitem);

}
