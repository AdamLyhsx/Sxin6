package com.adamly.xin6.service.impl;

import com.adamly.xin6.dao.UserDOMapper;
import com.adamly.xin6.dao.UserPasswordDOMapper;
import com.adamly.xin6.dataobject.UserDO;
import com.adamly.xin6.dataobject.UserPasswordDO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.error.EmBusinessErrror;
import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.UserModel;
import com.adamly.xin6.validator.ValidationResult;
import com.adamly.xin6.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    ValidatorImpl validator;

    @Override
    @Cacheable(value = "redisCache",key = "'redis_userModel_'+#id")
    public UserModel getUserById(Integer id) {
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);
        if(userDO==null){
            return null;
        }
        UserPasswordDO userPasswordDO= userPasswordDOMapper.selectByUserId(id);
        return convertFromDataObject(userDO,userPasswordDO);
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache",key = "'redis_userModel_'+#result.id")
    public UserModel register(UserModel userModel) throws BusinessException {
//        使用前参数校验（判空）
        if(userModel==null){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR);
        }
//        if(StringUtils.isEmpty(userModel.getTelphone())
//        ||StringUtils.isEmpty(userModel.getEncryptPassword())
//        ||StringUtils.isEmpty(userModel.getName())
//        ||userModel.getGender()==null
//        ||userModel.getAge()==null){
//            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR);
//        }
        ValidationResult result=validator.validate(userModel);
        if(result.isHasErr()==true){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

//        转换为dataobject，通过mapper插入数据库
        UserDO userDO=conventUserFromModel(userModel);
        try{
            userDOMapper.insertSelective(userDO);
        }catch(DuplicateKeyException ex){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,"此手机号已经注册过了");
        }
        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO=conventPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return userModel;
    }

    @Override
    public UserModel login(String telphone, String encrptPassword) throws BusinessException {
        UserDO userDO=userDOMapper.selectByTelphone(telphone);
        if(userDO==null){
            throw new BusinessException(EmBusinessErrror.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        if(!StringUtils.equals(userPasswordDO.getEncrptPassword(),encrptPassword)){
            throw new BusinessException(EmBusinessErrror.USER_LOGIN_FAIL);
        }
        return convertFromDataObject(userDO,userPasswordDO);
    }

    private UserDO conventUserFromModel(UserModel userModel)  {
        if(userModel==null){
            return null;
        }
        UserDO userDO =new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserPasswordDO conventPasswordFromModel(UserModel userModel) {
        if(userModel==null){
            return null;
        }
        UserPasswordDO userPasswordDO =new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncryptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        UserModel userModel=new UserModel();
        if(userDO==null){
            return null;
        }
        BeanUtils.copyProperties(userDO,userModel);
        if(userPasswordDO!=null){
            userModel.setEncryptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
