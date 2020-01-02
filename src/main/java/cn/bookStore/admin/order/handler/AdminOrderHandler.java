package cn.bookStore.admin.order.handler;

import cn.bookStore.admin.order.service.IAdminOrderService;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/21
 */
@Controller
@RequestMapping("/admin/orders")
public class AdminOrderHandler {
    @Autowired
    private IAdminOrderService orderService;

    @RequestMapping("/findOrders.do")
    public String findOrder(Model model, Order order, @RequestParam(defaultValue = "1") int pageIndex) {
        // System.out.println(order);
        PageModel pageModel = new PageModel();
        pageModel.setPageSize(8);
        int count = orderService.findOrderCount(order, pageModel);
        pageModel.setRecordCount(count);
        pageModel.setPageIndex(pageIndex);
        List<Order> orders = orderService.findOrder(order, pageModel);
        model.addAttribute("orders", orders);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("id", order.getId());
        model.addAttribute("receiverName", order.getReceiverName());
        return "/admin/orders/list.jsp";
    }

    @RequestMapping("/findOrderById.do")
    public String findOrderById(Model model, String id) {
        Order order = orderService.findOrderById(id);
        // System.out.println(order);
        model.addAttribute("order", order);
        return "/admin/orders/view.jsp";
    }

    @RequestMapping("delOrderById.do")
    public String delOrderById(String id) {
        int row = orderService.deleteOrderById(id);
        if (row > 0)
            return "redirect:/admin/orders/findOrders.do";
        else
            return "/client/fail.jsp";
    }

}
