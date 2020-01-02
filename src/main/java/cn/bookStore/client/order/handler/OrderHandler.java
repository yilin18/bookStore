package cn.bookStore.client.order.handler;

import cn.bookStore.client.order.service.IOrderService;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Orderitem;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.User;
import cn.bookStore.utils.AlipayConfig;
import cn.bookStore.utils.IdUtils;
import cn.bookStore.utils.PageModel;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/createOrder.do")
    public String createOrder(Order order, HttpSession session, Model model) throws Exception {
        HashMap<Product, Integer> cart = null;
        cart = (HashMap<Product, Integer>) session.getAttribute("cart");
        User user = (User) session.getAttribute("login_user");
        String id = IdUtils.getUUID();
        List<Orderitem> orderitems = new ArrayList<>();
        Set<Product> productSet = cart.keySet();
        for (Product product : productSet) {
            Orderitem orderitem = new Orderitem();
            int count = cart.get(product);
            orderitem.setBuynum(count);
            orderitem.setProduct_id(product.getId());
            orderitem.setOrder_id(id);
            orderitems.add(orderitem);

        }

        order.setOrderitems(orderitems);
        order.setId(id);
        order.setUser_id(user.getId());
        // System.out.println("创建订单" + order);
        orderService.creatOrder(order);
        session.removeAttribute("cart");
        model.addAttribute("order", order);
        return "/client/pay.jsp";

    }

    // @RequestMapping("/createOrder.do")
    // public String createOrder(Order order, HttpSession session, Model model) {
    //     HashMap<Product, Integer> cart = null;
    //     cart = (HashMap<Product, Integer>) session.getAttribute("cart");
    //     User user = (User) session.getAttribute("login_user");
    //     String id = IdUtils.getUUID();
    //     List<Orderitem> orderitems = new ArrayList<>();
    //     Set<Product> productSet = cart.keySet();
    //     for (Product product : productSet) {
    //         Orderitem orderitem = new Orderitem();
    //         int count = cart.get(product);
    //         orderitem.setBuynum(count);
    //         orderitem.setProduct_id(product.getId());
    //         orderitem.setOrder_id(id);
    //         orderitems.add(orderitem);
    //
    //     }
    //     // for (Orderitem orderitem1 : orderitems) {
    //     //     System.out.println("购物车里的商品" + orderitem1);
    //     // }
    //     order.setOrderitems(orderitems);
    //     order.setId(id);
    //     order.setUser_id(user.getId());
    //     System.out.println("创建订单" + order);
    //     int row = orderService.creatOrder(order);
    //     model.addAttribute("order", order);
    //     return "/client/pay.jsp";
    //
    // }
    @ResponseBody
    @RequestMapping(value = "/pay.do", produces = "text/html; charset=UTF-8")
    public String pay(String order_id, String money) throws AlipayApiException, IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order_id;
        //付款金额，必填
        String total_amount = money;
        //订单名称，必填
        String subject = "书城商品";
        //商品描述，可空
        String body = "喵~";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        // out.println(result);
        // response.getWriter().println(result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/paySuccessNotify.do")
    public String paySuccessNotify(HttpServletRequest request) throws Exception {
        // System.out.println("异步通知!");

        // return "success";
        //——请在这里编写您的程序（以下代码仅作参考）——
        boolean signVerified = fankui(request);
        // System.out.println(signVerified);
	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                return "fail";
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                // System.out.println("支付成功");
                Order order = orderService.findOrderByOrderId(out_trade_no);
                // System.out.println("异步里面结果" + order);
                if (order.getPaystate() == 0) {
                    orderService.modifyOrderStatus(out_trade_no);
                }

                return "success";
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            // System.out.println("验证签名成功");
            return "fail";

        } else {//验证失败
            // System.out.println("验证签名失败");
            return "fail";

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    @RequestMapping("/paySuccessReturn.do")
    public String paySuccessReturn(HttpServletRequest request) throws Exception {
        //获取支付宝GET过来反馈信息
        System.out.println("进入同步回调");
        boolean signVerified = fankui(request);
        System.out.println("同步验证签名成功"+signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            Order order = orderService.findOrderByOrderId(out_trade_no);
            if (order.getPaystate() == 0) {
                orderService.modifyOrderStatus(out_trade_no);
            }
            return "/client/paysuccess.jsp";
        } else {
            return "/client/fail.jsp";
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    private boolean fankui(HttpServletRequest request) throws Exception {
        /* *
         * 功能：支付宝服务器异步通知页面
         * 日期：2017-03-30
         * 说明：
         * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
         * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


         *************************页面功能说明*************************
         * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
         * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
         * 如果没有收到该页面返回的 success
         * 建议该页面只做支付成功的业务逻辑处理，退款的处理请以调用退款查询接口的结果为准。
         */

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        return signVerified;
    }

    @RequestMapping("findOrderByUser.do")
    public String findOrderByUser(Model model, HttpSession session, @RequestParam(defaultValue = "1") Integer pageIndex) {
        User user = (User) session.getAttribute("login_user");
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(orderService.findOrderCountByUser(user));
        List<Order> orders = orderService.findOrderByUser(pageModel, user);
        model.addAttribute("orders", orders);
        model.addAttribute("pageModel", pageModel);
        return "/client/orderlist.jsp";
    }

    @RequestMapping("/findOrderById.do")
    public String findOrderById(String id, Model model) {
        Order order = orderService.findOrderByOrderId(id);
        model.addAttribute("order", order);
        return "/client/orderInfo.jsp";
    }

    @RequestMapping("/delOrderById.do")
    public String delOrderById(String id, String type, Model model) {
        int count = 0;
        Order order = orderService.findOrderByOrderId(id);
        // System.out.println("订单详情"+order);
        List<Orderitem> orderItems = order.getOrderitems();
        for (Orderitem orderitem : orderItems) {
            // System.out.println("进入service的数据"+id+"type"+type+"orderitem"+orderitem);
            orderService.deleteOrderByOrderId(id, type, orderitem);
            count++;
        }
        if (count == orderItems.size()) {
            // System.out.println("删除这么多条"+count);
            return "/order/findOrderByUser.do";
        } else {
            model.addAttribute("msg", "删除失败!");
            return "/client/fail.jsp";
        }

    }

}
