package cn.bookStore.commons.beans;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/24
 */
public class ProductList {
    private String name;
    private Integer saleNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "name='" + name + '\'' +
                ", saleNum=" + saleNum +
                '}';
    }
}
