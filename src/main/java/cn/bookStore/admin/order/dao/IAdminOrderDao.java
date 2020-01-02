package cn.bookStore.admin.order.dao;

import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.ProductList;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/22
 */
public interface IAdminOrderDao {
    int selectOrderCount(Map map);


    Order selectOrderById(String id);

    List<Order> selectOrder(Map<String, Object> map);

    int deleteOrderById(String id);
}
