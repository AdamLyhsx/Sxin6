package com.adamly.xin6.service;

import com.adamly.xin6.response.BusinessException;
import com.adamly.xin6.service.model.GoodsModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/23 15:13
 */
public interface GoodsService {
//    商品创建服务
    GoodsModel createGoods(GoodsModel goodsModel) throws BusinessException;

//    商品列表服务
    List<GoodsModel> goodsList() throws JsonProcessingException;

//    商品详情服务
    GoodsModel getGoodsById(Integer id) throws BusinessException;

//    减库存服务
    boolean decreaseStock(Integer goodsId, Integer amount);

//    加销量服务
    void increaseSales(Integer id, Integer amount);

}
