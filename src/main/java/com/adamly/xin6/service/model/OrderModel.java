package com.adamly.xin6.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/24 20:40
 */
public class OrderModel implements Serializable {
    private String id;

    private Integer user_id;

    private Integer goods_id;

    private Integer promoId;

//    如果promoId非0则为活动单价
    private BigDecimal goods_price;

    private Integer amount;

//    如果promoId非0则为活动总价
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public BigDecimal getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(BigDecimal goods_price) {
        this.goods_price = goods_price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
