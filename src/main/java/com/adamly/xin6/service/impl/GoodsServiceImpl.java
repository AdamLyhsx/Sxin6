package com.adamly.xin6.service.impl;

import com.adamly.xin6.dao.GoodsDOMapper;
import com.adamly.xin6.dao.GoodsStockDOMapper;
import com.adamly.xin6.dataobject.GoodsDO;
import com.adamly.xin6.dataobject.GoodsStockDO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.error.EmBusinessErrror;
import com.adamly.xin6.service.GoodsService;
import com.adamly.xin6.service.PromoService;
import com.adamly.xin6.service.model.GoodsModel;
import com.adamly.xin6.service.model.PromoModel;
import com.adamly.xin6.validator.ValidationResult;
import com.adamly.xin6.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/23 15:25
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private GoodsDOMapper goodsDOMapper;
    @Autowired
    private GoodsStockDOMapper goodsStockDOMapper;
    @Autowired
    private PromoService promoService;

    @Override
    @Transactional
    public GoodsModel createGoods(GoodsModel goodsModel) throws BusinessException {
//        校验入参
        ValidationResult result=validator.validate(goodsModel);
        if(result.isHasErr()){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
//        model->do
        GoodsDO goodsDO=conventGoodsDoFromGoodsModel(goodsModel);
//        数据库入库
        goodsDOMapper.insertSelective(goodsDO);
        goodsModel.setId(goodsDO.getId());
        GoodsStockDO goodsStockDO=conventGoodsStockDoFromGoodsModel(goodsModel);
        goodsStockDOMapper.insertSelective(goodsStockDO);
//        返回model对象
        return this.getGoodsById(goodsModel.getId());
    }

    @Override
    public List<GoodsModel> goodsList() {
        List<GoodsDO> goodsDOList=goodsDOMapper.selectGoodsList();
        List<GoodsModel> goodsModelList=goodsDOList.stream().map(goodsDO -> {
            GoodsStockDO goodsStockDO=goodsStockDOMapper.selectByGoodsId(goodsDO.getId());
            GoodsModel goodsModel=this.conventGoodsModelFromDataObject(goodsDO,goodsStockDO);
            return goodsModel;
        }).collect(Collectors.toList());
        return goodsModelList;
    }

    @Override
    public GoodsModel getGoodsById(Integer id) throws BusinessException {
        if(id==null){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR,"id不能为空");
        }

        GoodsDO goodsDO=goodsDOMapper.selectByPrimaryKey(id);
        if(goodsDO==null){
            throw new BusinessException(EmBusinessErrror.GOODS_NUT_EXIST);
        }

        GoodsStockDO goodsStockDO=goodsStockDOMapper.selectByGoodsId(goodsDO.getId());
        if(goodsStockDO==null){
            throw new BusinessException(EmBusinessErrror.GOODS_NUT_EXIST);
        }
        GoodsModel goodsModel=conventGoodsModelFromDataObject(goodsDO,goodsStockDO);

        PromoModel promoModel=promoService.getPromoByGoodsId(id);
        if(promoModel!=null&&promoModel.getStatus()!=3){
            goodsModel.setPromoModel(promoModel);
        }
        return goodsModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer goodsId, Integer amount) {
        int affectedRow=goodsStockDOMapper.decreaseStock(goodsId,amount);
        if(affectedRow>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void increaseSales(Integer id, Integer amount) {
        goodsDOMapper.increaseSales(id,amount);
    }

    private GoodsDO conventGoodsDoFromGoodsModel(GoodsModel goodsModel){
        if(goodsModel==null){
            return null;
        }
        GoodsDO goodsDO=new GoodsDO();
        BeanUtils.copyProperties(goodsModel,goodsDO);
//        goodsDO.setPrice(goodsModel.getPrice().doubleValue());
        return goodsDO;
    }
    private GoodsStockDO conventGoodsStockDoFromGoodsModel(GoodsModel goodsModel){
        if(goodsModel==null){
            return null;
        }
        GoodsStockDO goodsStockDO=new GoodsStockDO();
        goodsStockDO.setStock(goodsModel.getStock());
        goodsStockDO.setGoodsId(goodsModel.getId());
        return goodsStockDO;
    }
    private GoodsModel conventGoodsModelFromDataObject(GoodsDO goodsDO,GoodsStockDO goodsStockDO){
        if(goodsDO==null||goodsStockDO==null){
            return null;
        }
        GoodsModel goodsModel=new GoodsModel();
        BeanUtils.copyProperties(goodsDO,goodsModel);
//        goodsModel.setPrice(new BigDecimal(goodsDO.getPrice()));
        goodsModel.setStock(goodsStockDO.getStock());
        return goodsModel;
    }


}
