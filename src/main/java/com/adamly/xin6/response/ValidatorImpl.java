package com.adamly.xin6.response;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
//对Hibernate Validator的封装类，封装了校验结果
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

//    bean生命周期的属性设置方法
    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator= Validation.buildDefaultValidatorFactory().getValidator();
    }

//    编写封装的校验方法并返回封装的校验结果
    public ValidationResult validate(Object bean){
        final ValidationResult result=new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet=validator.validate(bean);
        if(constraintViolationSet.size()>0){
            result.setHasErr(true);
            constraintViolationSet.forEach(constraintViolation->{
                result.getErrMsgMap().put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
            });
        }
        return result;
    }
}
