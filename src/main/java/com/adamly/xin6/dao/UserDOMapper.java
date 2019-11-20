package com.adamly.xin6.dao;

import com.adamly.xin6.dataobject.UserDO;
import com.adamly.xin6.dataobject.UserDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    long countByExample(UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int deleteByExample(UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    List<UserDO> selectByExample(UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    UserDO selectByPrimaryKey(Integer id);
    UserDO selectByTelphone(String telphone);
    Integer selectIdByTelphone(String telphone);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserDO record, @Param("example") UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int updateByExample(@Param("record") UserDO record, @Param("example") UserDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sun Oct 06 17:43:13 CST 2019
     */
    int updateByPrimaryKey(UserDO record);
}