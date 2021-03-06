package com.adamly.xin6.dataobject;

import java.math.BigDecimal;

public class GoodsDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.id
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.title
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.price
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.description
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.sales
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    private Integer sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.img_url
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    private String imgUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.id
     *
     * @return the value of goods_info.id
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.id
     *
     * @param id the value for goods_info.id
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.title
     *
     * @return the value of goods_info.title
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.title
     *
     * @param title the value for goods_info.title
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.price
     *
     * @return the value of goods_info.price
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.price
     *
     * @param price the value for goods_info.price
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.description
     *
     * @return the value of goods_info.description
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.description
     *
     * @param description the value for goods_info.description
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.sales
     *
     * @return the value of goods_info.sales
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.sales
     *
     * @param sales the value for goods_info.sales
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.img_url
     *
     * @return the value of goods_info.img_url
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.img_url
     *
     * @param imgUrl the value for goods_info.img_url
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}