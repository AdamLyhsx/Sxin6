package com.adamly.xin6.servicetest;

import com.adamly.xin6.TestBase;
import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/4/26 15:53
 */
public class UserServiceTest2 extends TestBase {
    @MockBean
    UserService userService;

    @Test
    public void testGetUserById1() {
        UserModel mockUserModel=new UserModel();
        mockUserModel.setId(1);
        mockUserModel.setName("ppxhhh");
        BDDMockito.given(userService.getUserById(1)).willReturn(mockUserModel);
        UserModel userModel=userService.getUserById(1);
        System.out.println(userModel.getName());
        Assert.assertNotNull(userModel);
    }
}
