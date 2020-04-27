package com.adamly.xin6.converter;

import com.adamly.xin6.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/22 17:02
 */
public class SexConverter implements AttributeConverter<SexEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
