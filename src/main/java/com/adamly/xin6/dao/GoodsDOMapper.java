package com.adamly.xin6.dao;

import com.adamly.xin6.dataobject.GoodsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_info
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_info
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    int insert(GoodsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_info
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    int insertSelective(GoodsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_info
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    GoodsDO selectByPrimaryKey(Integer id);
    List<GoodsDO> selectGoodsList();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_info
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_info
     *
     * @mbg.generated Wed Oct 23 15:50:42 CST 2019
     */
    int updateByPrimaryKey(GoodsDO record);

    int increaseSales(@Param("id") Integer id, @Param("amount") Integer amount);
}