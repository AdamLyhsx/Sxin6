package com.adamly.xin6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.adamly.xin6"})
public class Xin6Application {

//   作为类的入口启动了整个项目
    public static void main(String[] args) {
        SpringApplication.run(Xin6Application.class, args);
    }

}
