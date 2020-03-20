package com.adamly.xin6.controller.learnpojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/8 20:20
 */
@Component
@ConfigurationProperties(value = "person")
public class Teacher {
//    @Value("${person.teacherNumber}")
    private String teacherNumber;

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
