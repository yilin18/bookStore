package cn.bookStore.admin.order.service;

import cn.bookStore.admin.order.dao.IAdminOrderDao;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/22
 */
@Service
public class AdminOrderServiceImpl implements IAdminOrderService {
    @Resource
    private IAdminOrderDao orderDao;


    @Override
    public int findOrderCount(Order order, PageModel pageModel) {
        Map<String, Object> map = new HashMap();
        map.put("order", order);
        map.put("pageModel", pageModel);
        return orderDao.selectOrderCount(map);
    }

    @Override
    public List<Order> findOrder(Order order, PageModel pageModel) {
        Map<String, Object> map = new HashMap();
        map.put("order", order);
        map.put("pageModel", pageModel);
        return orderDao.selectOrder(map);
    }

    @Override
    public Order findOrderById(String id) {
        return orderDao.selectOrderById(id);
    }

    @Override
    public int deleteOrderById(String id) {
        return orderDao.deleteOrderById(id);
    }


}
