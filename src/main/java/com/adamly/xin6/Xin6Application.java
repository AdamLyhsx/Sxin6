package com.adamly.xin6;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

@SpringBootApplication(scanBasePackages = {"com.adamly.xin6"})
@MapperScan("com.adamly.xin6.dao")
@EnableCaching
@PropertySource(value = {"classpath:my.properties"}, ignoreResourceNotFound = true)
public class Xin6Application {
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private RedisConnectionFactory connectionFactory = null;
    @Autowired
    private MessageListener redisMsgListener = null;
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler = null;
    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor =null;


    @PostConstruct
    public void init() {
        initRedisTemPlate();
    }

    //    配置RedisTemPlate使用字符串序列化器
    private void initRedisTemPlate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    //    装配等待消息的任务调度线程池(redis)
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    //    装配reds消息的监听容器
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setTaskExecutor(initTaskScheduler());
        container.addMessageListener(redisMsgListener, new ChannelTopic("topic1"));
        return container;
    }

    // 装配国际化解析器
    @Bean(name="localeResolver")
    public LocaleResolver initLocaleResolver(){
        SessionLocaleResolver slr=new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    //装配国际化拦截器
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        if(localeChangeInterceptor != null){
            return localeChangeInterceptor;
        }
        localeChangeInterceptor =new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }



    //   作为类的入口启动了整个项目
    public static void main(String[] args) {
        SpringApplication.run(Xin6Application.class, args);
    }

}
