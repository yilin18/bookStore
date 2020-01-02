package cn.bookStore.client.product.handler;

import cn.bookStore.client.product.service.IProductService;
import cn.bookStore.commons.beans.Notice;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Product;
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
 * Create Data: 2019/4/26
 */
@RequestMapping("/product")
@Controller
public class ProductHandler {
    @Autowired
    private IProductService productService;

    @RequestMapping("/findProduct.do")
    public String findProductByCategory(@RequestParam(defaultValue = "1") Integer pageIndex, Model model, String category) {
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        int count = productService.findProductCountByCategory(category);
        pageModel.setRecordCount(count);
        List<Product> products = productService.findProductByCategory(category, pageModel);
        model.addAttribute("products", products);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("category", category);
        return "/client/product_list.jsp";
    }

    @RequestMapping("/findProductByName.do")
    public String findProductByName(@RequestParam(defaultValue = "1") Integer pageIndex, Model model, String textfield) {
        //System.out.println("搜索" + textfield);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        int count = productService.findProductCountByName(textfield);
        //System.out.println("数量" + count);
        pageModel.setRecordCount(count);
        List<Product> products = productService.findProductByName(textfield, pageModel);
        // for (Product product : products) {
        //     System.out.println(product);
        // }
        model.addAttribute("name", textfield);
        model.addAttribute("products", products);
        model.addAttribute("pageModel", pageModel);
        return "/client/product_search_list.jsp";

    }

    @RequestMapping("/findProductById.do")
    public String findProductById(Model model, String id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "/client/info.jsp";

    }

    @RequestMapping("/addCart.do")
    public String addCart(Model model, String id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "/client/info.jsp";

    }

    @RequestMapping("/findNotice.do")
    public String findNotice(Model model) {
        // System.out.println("dasda");
        Notice notice = productService.findNotice();
        List<Product> products = productService.findHotProducts();
        int size = products.size();
        // System.out.println(size + "haha" + products);

        // System.out.println(notice);
        model.addAttribute("products", products);
        model.addAttribute("notice", notice);
        return "/client/index.jsp";
    }


}
