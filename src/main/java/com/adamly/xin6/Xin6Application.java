package com.adamly.xin6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.adamly.xin6"})
@EnableCaching
public class Xin6Application {
    @Autowired
    private RedisTemplate redisTemplate=null;

    @PostConstruct
    public void init(){
        initRedisTemPlate();
    }
    private void initRedisTemPlate(){
        RedisSerializer stringSerializer=redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }


//   作为类的入口启动了整个项目
    public static void main(String[] args) {
        SpringApplication.run(Xin6Application.class, args);
    }

}
