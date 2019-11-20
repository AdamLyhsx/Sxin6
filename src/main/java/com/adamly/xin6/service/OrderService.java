package com.adamly.xin6.service;

import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.service.model.OrderModel;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/24 21:06
 */
public interface OrderService {
//    创建交易的服务
    public OrderModel createOrder(Integer userId,Integer goodsId,Integer promoId,Integer amount) throws BusinessException;
}
