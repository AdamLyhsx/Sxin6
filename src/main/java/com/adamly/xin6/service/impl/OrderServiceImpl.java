package com.adamly.xin6.service.impl;

import com.adamly.xin6.dao.OrderDOMapper;
import com.adamly.xin6.dao.SequenceDOMapper;
import com.adamly.xin6.dataobject.OrderDO;
import com.adamly.xin6.dataobject.SequenceDO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.error.EmBusinessErrror;
import com.adamly.xin6.service.GoodsService;
import com.adamly.xin6.service.OrderService;
import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.GoodsModel;
import com.adamly.xin6.service.model.OrderModel;
import com.adamly.xin6.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/24 21:12
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer goodsId,Integer promoId, Integer amount) throws BusinessException {
//        1校验 用户、商品、活动、订单
//        下单状态、用户是否合法、下单的商品是否存在、购买数量是否正确、商品与活动是否完全对应
        UserModel userModel=userService.getUserById(userId);
        if(userModel==null){
            throw new BusinessException(EmBusinessErrror.USER_NUT_EXIST);
        }
        GoodsModel goodsModel=goodsService.getGoodsById(goodsId);
        if(goodsModel==null){
            throw new BusinessException(EmBusinessErrror.GOODS_NUT_EXIST);
        }
        if(amount<=0||amount>=100){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,"商品数量必须在0~100");
        }
        if(promoId!=null){
            if(promoId.intValue()!=goodsModel.getPromoModel().getId()){
                throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,"此商品未参加对应活动");
            }else if(goodsModel.getPromoModel().getStatus()!=2){
                throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,"此商品活动现在未进行");
            }
        }
//        2落单减库存/支付减库存
        boolean result=goodsService.decreaseStock(goodsId,amount);
        if(!result){
            throw new BusinessException(EmBusinessErrror.STOCK_NOT_ENOUGH);
        }
//        3订单入库
        OrderModel orderModel=new OrderModel();
//        生成交易号设置交易id
        orderModel.setId(generateOrderNO());
        orderModel.setUser_id(userId);
        orderModel.setGoods_id(goodsId);
        orderModel.setPromoId(promoId);
        if(promoId!=null){
            orderModel.setGoods_price(goodsModel.getPromoModel().getPromoGoodsPrice());
        }else{
            orderModel.setGoods_price(goodsModel.getPrice());
        }
        orderModel.setAmount(amount);
        orderModel.setOrderPrice(orderModel.getGoods_price().multiply(new BigDecimal(amount)));
        OrderDO orderDO=this.conventDOfromModel(orderModel);
        orderDOMapper.insertSelective(orderDO);
//        4入库成功加销量
        goodsService.increaseSales(goodsId,amount);
//        5返回数据
        return orderModel;
    }

    private OrderDO conventDOfromModel(OrderModel orderModel){
        if(orderModel ==null){
            return null;
        }
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        orderDO.setUserId(orderModel.getUser_id());
        orderDO.setGoodsId(orderModel.getGoods_id());
        orderDO.setGoodsPrice(orderModel.getGoods_price());
//        orderDO.setGoodsPrice(orderModel.getGoods_price().doubleValue());
//        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNO(){
        StringBuilder stringBuilder=new StringBuilder();
//        八位时间信息
        LocalDateTime now=LocalDateTime.now();
        stringBuilder.append(now.format(DateTimeFormatter.ISO_DATE).replace("-",""));
//        六位自增序列
//        可能高于六位故应设置最大值与初始值，保证要超过六位时重新开始
        SequenceDO sequenceDO=sequenceDOMapper.getSequenceByName("order_info");
        int sequence=sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequence+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr=String.valueOf(sequence);
        for(int i=0;i<6-sequenceStr.length();i++){
            stringBuilder.append("0");
        }
        stringBuilder.append(sequence);
//        两位分库分表位，暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

}
