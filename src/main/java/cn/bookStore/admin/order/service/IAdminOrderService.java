package cn.bookStore.admin.order.service;

import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.ProductList;
import cn.bookStore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/21
 */
public interface IAdminOrderService {


    int findOrderCount(Order order, PageModel pageModel);

    List<Order> findOrder(Order order, PageModel pageModel);

    Order findOrderById(String id);

    int deleteOrderById(String id);
}
