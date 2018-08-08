package com.anjie.apply.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by sang on 2016/12/30.
 */
@Entity
public class test {
    @Id
    @GeneratedValue
    private Long id;
    //订单号
    private String orderNo;
    //产品分类
    private String product;
    //产品类型
    private String buyType;
    //价格
    private String price;
    //购买数量
    private String buyNumber;
    //有效时间
    private String period;
    //产品版本
    private String version;
    //银行id
    private String bankId;
    //购买日期
    private String buyDate;
    //订单状态
    private String orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(String buyNumber) {
        this.buyNumber = buyNumber;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getDate() {
        return buyDate;
    }

    public void setDate(String date) {
        this.buyDate = date;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public test(String orderNo, String product, String buyType, String price, String buyNumber, String period, String version, String bankId, String buyDate, String orderStatus) {
        this.orderNo = orderNo;
        this.product = product;
        this.buyType = buyType;
        this.price = price;
        this.buyNumber = buyNumber;
        this.period = period;
        this.version = version;
        this.bankId = bankId;
        this.buyDate = buyDate;
        this.orderStatus = orderStatus;
    }

    public test() {
    }


}
