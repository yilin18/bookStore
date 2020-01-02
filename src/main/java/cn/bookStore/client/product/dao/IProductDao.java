package cn.bookStore.client.product.dao;

import cn.bookStore.commons.beans.Notice;
import cn.bookStore.commons.beans.Order;
import cn.bookStore.commons.beans.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/26
 */
public interface IProductDao {
    List<Product> selectProductByCategory(Map map);

    int selectProductCountByCategory(@Param("category") String category);

    int selectProductCountByName(@Param("name") String name);

    List<Product> selectProductByName(Map map);

    Product selectProductById(@Param("id") String id);

    Notice selectNotice();

    List<Product> selectHotProducts();
}
