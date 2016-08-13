package com.example.config;

import com.example.service.CISDetailService;
import com.example.service.CISGroupService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
public class MainConfiguration {

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public StringHttpMessageConverter responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converter.setWriteAcceptCharset(false);
        return converter;
    }

    @Bean
    public CISGroupService cisGroupService(){
        return new CISGroupService();
    }

    @Bean
    public CISDetailService cisDetailService(){
        return new CISDetailService();
    }

}
