package cn.bookStore.client.product.service;

import cn.bookStore.commons.beans.Notice;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/26
 */
public interface IProductService {
    List<Product> findProductByCategory(String category, PageModel pageModel);

    int findProductCountByCategory(String category);

    int findProductCountByName(String textfield);

    List<Product> findProductByName(String textfield,PageModel pageModel);

    Product findProductById(String id);

    Notice findNotice();

    List<Product> findHotProducts();
}
