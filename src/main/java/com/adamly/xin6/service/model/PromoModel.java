package com.adamly.xin6.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/26 16:01
 */
public class PromoModel {
    private Integer id;

//    活动状态123
    private Integer status;

    private String name;

    private DateTime startDate;

    private DateTime endDate;

    private Integer goodsId;

//    模型聚合
    private BigDecimal promoGoodsPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPromoGoodsPrice() {
        return promoGoodsPrice;
    }

    public void setPromoGoodsPrice(BigDecimal promoGoodsPrice) {
        this.promoGoodsPrice = promoGoodsPrice;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
