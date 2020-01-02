package cn.bookStore.client.product.service;

import cn.bookStore.client.product.dao.IProductDao;
import cn.bookStore.commons.beans.Notice;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/26
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Resource
    private IProductDao productDao;
    @Override
    public List<Product> findProductByCategory(String category, PageModel pageModel) {
        Map map =new HashMap();
        map.put("category",category);
        map.put("pageModel",pageModel);
        return productDao.selectProductByCategory(map);
    }

    @Override
    public int findProductCountByCategory(String category) {
        return productDao.selectProductCountByCategory(category);
    }

    @Override
    public int findProductCountByName(String textfield) {
        return productDao.selectProductCountByName(textfield);
    }

    @Override
    public List<Product> findProductByName(String textfield ,PageModel pageModel) {
        Map map=new HashMap();
        map.put("name",textfield);
        map.put("pageModel",pageModel);
        return productDao.selectProductByName(map);
    }

    @Override
    public Product findProductById(String id) {
        return productDao.selectProductById(id);
    }

    @Override
    public Notice findNotice() {
        return productDao.selectNotice();
    }

    @Override
    public List<Product> findHotProducts() {
        return productDao.selectHotProducts();
    }
}
