package cn.bookStore.admin.product.dao;

import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.ProductList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/22
 */
public interface IAdminProductDao {
    int selectProductCount(Map map);

    List<Product> selectProduct(Map map);

    Product selectProductById(String id);

    int updateProductById(Product product);

    int insertProduct(Product product);

    int deleteProduct(String id);

    List<ProductList> selectProductList(Map map);

    // List<ProductList> selectProductList(@Param("year") Integer year, @Param("month") Integer month);
}
