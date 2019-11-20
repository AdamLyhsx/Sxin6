package com.adamly.xin6.controller;

import com.adamly.xin6.dao.UserDOMapper;
import com.adamly.xin6.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan("com.adamly.xin6.dao")
public class TestController {

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/my")
    public String my(){
        UserDO userDO=userDOMapper.selectByPrimaryKey(1);
        if(userDO==null){
            return "用户不存在";
        }else {
            return userDO.getName();
        }
    }
}
