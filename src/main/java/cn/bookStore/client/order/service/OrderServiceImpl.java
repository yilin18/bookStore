package cn.bookStore.client.order.service;

import cn.bookStore.client.order.dao.IOrderDao;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Orderitem;
import cn.bookStore.commons.beans.User;
import cn.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/6
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private IOrderDao orderDao;

    @Override
    public int creatOrder(Order order) throws Exception {
        int row = orderDao.insertOrder(order);
        List<Orderitem> orderItems = order.getOrderitems();
        // if (1==1){
        //     throw new Exception();
        // }
        for (Orderitem orderitem : orderItems) {
            orderDao.insertOrderItem(orderitem);
            orderDao.changeProductNum(orderitem);
        }

        return row;
    }

    @Override
    public Order findOrderByOrderId(String out_trade_no) {
        return orderDao.selectOrderByOrderId(out_trade_no);
    }

    @Override
    public void modifyOrderStatus(String out_trade_no) {
        orderDao.updateOrderStatus(out_trade_no);
    }

    @Override
    public List<Order> findOrderByUser(PageModel pageModel, User user) {
        Map map = new HashMap();
        map.put("pageModel", pageModel);
        map.put("user", user);
        return orderDao.selectOrderByUser(map);
    }

    @Override
    public int findOrderCountByUser(User user) {
        return orderDao.findOrderCountByUser(user);
    }

    @Override
    public void deleteOrderByOrderId(String id, String type, Orderitem orderitem) {
        // System.out.println(type);
        if ("client".equals(type)) {
            // System.out.println("改变商品数量"+orderitem);
            orderDao.changeProductNumberByItem(orderitem);
        }
        orderDao.deleteOrderByOrderId(id);
    }

}
