package com.adamly.xin6.service;

import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.service.model.UserModel;

public interface UserService {
//    用户信息获取服务
    UserModel getUserById(Integer id);

//    用户注册服务
//    void register(UserModel userModel) throws BusinessException;
    UserModel register(UserModel userModel) throws BusinessException;

//    用户登录服务
//    void login(String telphone,String encrptPassword) throws BusinessException;
    UserModel login(String telphone,String encrptPassword) throws BusinessException;
}
