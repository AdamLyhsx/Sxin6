package com.adamly.xin6.dao;

import com.adamly.xin6.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbg.generated Sun Oct 27 16:19:57 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbg.generated Sun Oct 27 16:19:57 CST 2019
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbg.generated Sun Oct 27 16:19:57 CST 2019
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbg.generated Sun Oct 27 16:19:57 CST 2019
     */
    PromoDO selectByPrimaryKey(Integer id);

    PromoDO selectByGoodsId(Integer goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbg.generated Sun Oct 27 16:19:57 CST 2019
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbg.generated Sun Oct 27 16:19:57 CST 2019
     */
    int updateByPrimaryKey(PromoDO record);
}