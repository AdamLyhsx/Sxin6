package com.adamly.xin6;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/4/26 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBase {
    @Before
    public void before(){
        System.out.println("测试开始");
    }

    @After
    public void after(){
        System.out.println("测试结束");
    }

}
