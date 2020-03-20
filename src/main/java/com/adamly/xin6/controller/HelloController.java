package com.adamly.xin6.controller;

import com.adamly.xin6.controller.learnpojo.Teacher;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
public class HelloController {
    @Autowired
    Teacher teacher;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest request) {
        System.out.println("hello--");
//        log.trace("trace level");
//        log.debug("debug level");
//        log.info("info level");
//        log.warn("warn level");
//        log.error("error level");
//        int i = 1 / 0;
//        System.out.println(teacher.getTeacherNumber());
        return "Hello Spring Boot!123";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(HttpServletRequest request) {
        System.out.println("hello2--");
        return "hello2";
    }

}
