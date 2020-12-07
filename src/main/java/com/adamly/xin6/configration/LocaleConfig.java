package com.adamly.xin6.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/12/7 9:29
 */
@Configuration
public class LocaleConfig {
    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor =null;

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
}
