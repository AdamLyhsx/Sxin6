package com.adamly.xin6.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/6 22:01
 */
@Component
@Aspect
public class MyAspect1 {
    @Pointcut(value = "execution(* com.adamly.xin6.controller.HelloController.hello(..) )")
    public void  helloCut(){

    }

    @Before("helloCut()")
    public void helloBefore(){
        System.out.println("hello before...");
    }

}
