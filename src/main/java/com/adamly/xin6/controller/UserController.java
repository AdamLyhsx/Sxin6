package com.adamly.xin6.controller;


import com.adamly.xin6.controller.viewobject.UserVO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.error.EmBusinessErrror;
import com.adamly.xin6.response.CommonReturnType;
import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

//@Controller("user")
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;

    //    通过id获取用户数据的接口
    @RequestMapping("/getuser")
//    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
//        数据库查找用户数据
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
//            userModel.setEncryptPassword("123");
            throw new BusinessException(EmBusinessErrror.USER_NUT_EXIST);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    //    获取otp短信的接口
    @RequestMapping(value = "/getotp", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMD})
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
        Random random = new Random();
        int randomInt = random.nextInt(99999) + 100000;
        String otpCode = String.valueOf(randomInt);

        httpServletRequest.getSession().setAttribute(telphone, otpCode);
//        调用接口发送otp验证码短信...
        System.out.println("telphone: " + telphone + " otpCode: " + otpCode);
        return CommonReturnType.create(null);

    }

    //    用户注册接口
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMD})
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Byte gender,
                                     @RequestParam(name = "age") Integer age) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        验证手机号与短信验证码是否一致
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!com.alibaba.druid.util.StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR, "短信验证码错误");
        }
//        password校验与加密
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        } else if (password.length() < 2) {
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR, "密码至少为两位");
        }
        String encryptPassword = this.EncodeByMd5(password);
//        用户注册服务(数据库中插入用户数据)
        UserModel userModel = new UserModel();
        userModel.setEncryptPassword(encryptPassword);
        userModel.setTelphone(telphone);
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userService.register(userModel);
//        中间无异常终止才会到这一步，则必然已成功完成注册相关操作，则返回success
        return CommonReturnType.create(null);
    }
////  用户登录接口
//    @RequestMapping(value="/login",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMD})
//    public CommonReturnType login(@RequestParam(name="telphone")String telphone,
//                                  @RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
////        参数校验
//        if(StringUtils.isEmpty(telphone)||StringUtils.isEmpty(password)){
//            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR);
//        }
////       用户登录服务
//        userService.login(telphone,this.EncodeByMd5(password));
////
//
//        return CommonReturnType.create(null);
//    }

    //  用户登录接口
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMD})
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        参数校验
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR);
        }
//       用户登录服务
        UserModel userModel = userService.login(telphone, this.EncodeByMd5(password));
//       在session中加入用户登录凭证
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }


    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        return base64en.encode(md5.digest(str.getBytes("utf-8")));
    }

    private UserVO convertFromModel(UserModel userModel) {
        UserVO userVO = new UserVO();
        if (userModel == null) {
            return null;
        }
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
