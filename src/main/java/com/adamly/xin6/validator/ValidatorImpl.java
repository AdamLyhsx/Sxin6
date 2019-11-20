package com.adamly.xin6.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

//    这个bean初始化后回调这个方法完成真正的初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator= Validation.buildDefaultValidatorFactory().getValidator();
    }

//    实现真正的校验方法并返回校验结果
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
