package com.adamly.xin6.service;

import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.service.model.UserModel;
import com.adamly.xin6.dataobject.UserDO;

import java.util.List;

//用户服务
public interface UserService {
//    id用户服务
    UserModel getUserById(Integer id);

//    用户注册服务
//    void register(UserModel userModel) throws BusinessException;
    UserModel register(UserModel userModel) throws BusinessException;

//    用户登录服务
//    void login(String telphone,String encrptPassword) throws BusinessException;
    UserModel login(String telphone,String encrptPassword) throws BusinessException;

//    用户列表服务 learn
    List<UserDO> userList() throws BusinessException;

//    name列表服务 learn
    List<UserDO> getUserByName(String name) throws BusinessException;

}
