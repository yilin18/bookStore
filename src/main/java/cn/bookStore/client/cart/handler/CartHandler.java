package cn.bookStore.client.cart.handler;

import cn.bookStore.client.product.service.IProductService;
import cn.bookStore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/3
 */
@RequestMapping("/cart")
@Controller
public class CartHandler {
    @Autowired
    private IProductService productService;

    @RequestMapping("/addCart.do")
    public String addCar(String id,  HttpSession session) {

        Product product = productService.findProductById(id);
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Product, Integer>();
        }
        //map.put的返回值为已存在键的value值
        Integer count = cart.put(product, 1);
        if (count != null) {
            cart.put(product, count + 1);
        }
        session.setAttribute("cart", cart);

        return "/client/cart.jsp";
    }

    @RequestMapping("/changeCart.do")
    public String changeCart(String id, Integer count, HttpSession session) {
        Product product = productService.findProductById(id);
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        cart.put(product, count);

        if(count==0){
            cart.remove(product);
        }
        session.setAttribute("cart", cart);

        return "/client/cart.jsp";
    }
}
