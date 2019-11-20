package com.adamly.xin6.controller;

import com.adamly.xin6.controller.viewobject.GoodsVO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.response.CommonReturnType;
import com.adamly.xin6.service.GoodsService;
import com.adamly.xin6.service.model.GoodsModel;
import com.adamly.xin6.service.model.PromoModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/23 16:30
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

//    创建商品的接口
    @RequestMapping(value="/create",method = RequestMethod.POST,consumes = {CONTENT_TYPE_FORMD})
    public CommonReturnType createGoods(@RequestParam(name="title")String title,
                                        @RequestParam(name="price")BigDecimal price,
                                        @RequestParam(name="stock")Integer stock,
                                        @RequestParam(name="description")String description,
                                        @RequestParam(name="imgUrl")String imgUrl) throws BusinessException {
//       调用商品创建服务
        int a;
        Integer stok;
        GoodsModel goodsModel=new GoodsModel();
        goodsModel.setTitle(title);
        goodsModel.setPrice(price);
        goodsModel.setStock(stock);
        goodsModel.setDescription(description);
        goodsModel.setImgUrl(imgUrl);
        GoodsModel goodsModelFromReturn=goodsService.createGoods(goodsModel);
//        model->vo
        return CommonReturnType.create(conventVOFromModel(goodsModel));
    }

//    查看商品列表的接口
    @RequestMapping(value = "/goodslist",method = RequestMethod.GET)
    public CommonReturnType getGoodsList() throws BusinessException {
        List<GoodsModel> goodsModelList=goodsService.goodsList();

        List<GoodsVO> goodsVOList=goodsModelList.stream().map(goodsModel -> {
            return this.conventVOFromModel(goodsModel);
        }).collect(Collectors.toList());

        return CommonReturnType.create(goodsVOList);
    }


//    查看商品详情的接口(get goods by id)
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public CommonReturnType getGoods(@RequestParam("id")Integer id) throws BusinessException {
        GoodsModel goodsModel=goodsService.getGoodsById(id);
        return CommonReturnType.create(conventVOFromModel(goodsModel));
    }


    private GoodsVO conventVOFromModel(GoodsModel goodsModel){
        if(goodsModel==null){
            return null;
        }
        GoodsVO goodsVO=new GoodsVO();
        BeanUtils.copyProperties(goodsModel,goodsVO);
        PromoModel promoModel=goodsModel.getPromoModel();
        if(promoModel!=null){
            goodsVO.setPromoId(promoModel.getId());
            goodsVO.setPromoStatus(promoModel.getStatus());
            goodsVO.setPromoName(promoModel.getName());
            goodsVO.setStartDate(promoModel.getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            goodsVO.setEndDate(promoModel.getEndDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            goodsVO.setPromoPrice(promoModel.getPromoGoodsPrice());
        }else{
            goodsVO.setPromoStatus(0);
        }
        return goodsVO;
    }
}
