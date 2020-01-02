package cn.bookStore.admin.product.service;

import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.ProductList;
import cn.bookStore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/21
 */
public interface IAdminProductService {

    List<Product> findProduct(Product product, PageModel pageModel, Integer maxPrice, Integer minPrice);

    int findProductCount(Product product, PageModel pageModel, Integer maxPrice, Integer minPrice);

    Product findProductById(String id);

    int modifyProductById(Product product);

    int addProduct(Product product);

    int deleteProductById(String id);

    List<ProductList> findProductList(Integer year, Integer month);
}
