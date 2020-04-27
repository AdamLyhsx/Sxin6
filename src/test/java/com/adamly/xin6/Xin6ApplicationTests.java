package com.adamly.xin6;

import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Xin6ApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        System.out.println("测试开始");
        UserModel userModel=userService.getUserById(1);
//        UserModel userModel=null;
        Assert.assertNotNull(userModel);
        System.out.println("测试结束");
    }



}
