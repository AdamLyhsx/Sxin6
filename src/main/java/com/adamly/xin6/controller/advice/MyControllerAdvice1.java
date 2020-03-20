package com.adamly.xin6.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/19 11:40
 */
@ControllerAdvice(basePackages = {"com.adamly.xin6.controller.*"},annotations = {Controller.class})
public class MyControllerAdvice1 {


}
