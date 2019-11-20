package com.adamly.xin6.controller;

import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.error.EmBusinessErrror;
import com.adamly.xin6.response.CommonReturnType;
import com.adamly.xin6.service.OrderService;
import com.adamly.xin6.service.model.OrderModel;
import com.adamly.xin6.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/25 21:31
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;

//    下单(创建订单)接口
    @RequestMapping(value="/createorder",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMD})
    public CommonReturnType createOrder(@RequestParam(name="goodsId") Integer goodsId,
                                        @RequestParam(name="promoId",required = false) Integer promoId,
                                        @RequestParam(name="amount") Integer amount) throws BusinessException {
//      验证用户是否登录，从session中获取对应用户的id
        Boolean isLogin=(Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin==null||!isLogin.booleanValue()){
            throw new BusinessException(EmBusinessErrror.USER_NOT_LOGIN);
        }
        UserModel userModel=(UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
//      调用订单创建服务
        OrderModel orderModel=orderService.createOrder(userModel.getId(),goodsId,promoId,amount);
        return CommonReturnType.create(orderModel);
    }

}
