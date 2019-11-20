package com.adamly.xin6.service.impl;

import com.adamly.xin6.dao.PromoDOMapper;
import com.adamly.xin6.dataobject.PromoDO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.error.EmBusinessErrror;
import com.adamly.xin6.service.PromoService;
import com.adamly.xin6.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/27 16:23
 */
@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByGoodsId(Integer goodsId) throws BusinessException {
        if(goodsId==null){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR);
        }
        PromoDO promoDO=promoDOMapper.selectByGoodsId(goodsId);
        if(promoDO==null){
            return null;
        }
        PromoModel promoModel=this.conventDOFromModel(promoDO);
        if(promoModel==null){
            throw new BusinessException(EmBusinessErrror.PARAMETER_VALIDATION_ERROR);
        }

        if(promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);
        }else if(promoModel.getEndDate().isAfterNow()){
            promoModel.setStatus(2);
        }else{
            promoModel.setStatus(3);
        }

        return promoModel;
    }


    private PromoModel conventDOFromModel(PromoDO promoDO ){
        PromoModel promoModel=new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
//        System.out.println(LocalDateTime.now());
//        System.out.println(promoDO.getStartDate());
//        System.out.println(promoModel.getStartDate());
        return promoModel;
    }

}
