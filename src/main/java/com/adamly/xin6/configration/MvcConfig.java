package com.adamly.xin6.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.adamly.xin6.device.interceptor.*;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/13 22:27
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir1 = registry.addInterceptor(new MyInterceptor1());
        ir1.addPathPatterns("/learnmvc/*");
        registry.addInterceptor(localeChangeInterceptor);
    }
}
