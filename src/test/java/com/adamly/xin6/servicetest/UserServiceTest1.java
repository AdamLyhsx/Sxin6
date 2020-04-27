package com.adamly.xin6.servicetest;

import com.adamly.xin6.TestBase;
import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/4/26 15:53
 */
public class UserServiceTest1 extends TestBase {
    @Autowired
    UserService userService;

    @Test
    public void testGetUserById1() {
        UserModel userModel=userService.getUserById(1);
        Assert.assertNotNull(userModel);
    }

    @Test
    public void testGetUserByIdTest2() {
        UserModel userModel=null;
        Assert.assertNotNull(userModel);
    }
}
