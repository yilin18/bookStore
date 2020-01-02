package cn.bookStore.admin.product.service;

import cn.bookStore.admin.product.dao.IAdminProductDao;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.ProductList;
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
public class AdminProductServiceImpl implements IAdminProductService {
    @Resource
    private IAdminProductDao productDao;


    @Override
    public List<Product> findProduct(Product product, PageModel pageModel, Integer maxPrice, Integer minPrice) {
        Map map=new HashMap();
        map.put("product",product);
        map.put("pageModel",pageModel);
        map.put("maxPrice",maxPrice);
        map.put("minPrice",minPrice);
        List<Product> products=productDao.selectProduct(map);
        return products;
    }

    @Override
    public int findProductCount(Product product, PageModel pageModel, Integer maxPrice, Integer minPrice) {
        Map map=new HashMap();
        map.put("product",product);
        map.put("pageModel",pageModel);
        map.put("maxPrice",maxPrice);
        map.put("minPrice",minPrice);
        int count =productDao.selectProductCount(map);
        return count;
    }

    @Override
    public Product findProductById(String id) {
        return productDao.selectProductById(id);
    }

    @Override
    public int modifyProductById(Product product) {
        return productDao.updateProductById(product);
    }

    @Override
    public int addProduct(Product product) {
        return productDao.insertProduct(product);
    }

    @Override
    public int deleteProductById(String id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public List<ProductList> findProductList(Integer year, Integer month) {
        Map<String,Integer> map=new HashMap();
        map.put("year",year);
        map.put("month",month);
        // System.out.println("map_year"+map.get("year"));
        // System.out.println("map_month"+map.get("month"));

        return productDao.selectProductList(map);
    }
}
