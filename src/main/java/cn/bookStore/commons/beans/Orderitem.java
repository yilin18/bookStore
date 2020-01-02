package cn.bookStore.commons.beans;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/6
 */
public class Orderitem {
    private String order_id;
    private String product_id;
    private Integer buynum;
    private Product product;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "order_id='" + order_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", buynum=" + buynum +
                ", product=" + product +
                '}';
    }
}
