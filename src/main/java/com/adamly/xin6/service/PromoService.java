package com.adamly.xin6.service;

import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.service.model.PromoModel;

/**
 * @author adamly
 * @version 1.0
 * @date 2019/10/27 16:22
 */
public interface PromoService {
    PromoModel getPromoByGoodsId(Integer goodsId) throws BusinessException;
}
