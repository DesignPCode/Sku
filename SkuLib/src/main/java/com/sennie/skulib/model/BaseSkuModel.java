package com.sennie.skulib.model;

/**
 * 项目名称：Sku
 * 类描述：Sku基本模型数据
 * 创建人：N.Sun
 * 创建时间：2016/11/24 下午3:27
 * 修改人：N.Sun
 * 修改时间：2016/11/24 下午3:27
 * 修改备注：
 */
public class BaseSkuModel {

    //base 属性
    private double price;//价格
    private long stock;//库存

    public BaseSkuModel(double price, long stock) {
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
