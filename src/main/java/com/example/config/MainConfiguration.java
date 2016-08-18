package com.example.config;

import com.example.service.CISDetailService;
import com.example.service.CISGroupService;
import com.example.web.GroupController;
import com.google.common.primitives.Ints;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

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

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix="datasource.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }


//    @Bean
//    public FilterRegistrationBean corsFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new CORSFilter());
//        registrationBean.setName("CORS");
//        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
//        registrationBean.setMatchAfter(true);
//        registrationBean.setInitParameters(new HashMap<String, String>() {{
//            put("Access-Control-Allow-Origin", "*");
//            put("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        }});
//        registrationBean.setUrlPatterns(Collections.singletonList("*"));
//
//        return registrationBean;
//    }

//    @Bean
//    public FreeMarkerConfigurationFactory freeMarkerConfigurationFactory(){
//        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
//        factory.setTemplateLoaderPath("classpath:templates");
//        factory.setPreferFileSystemAccess(false);
//        factory.setDefaultEncoding("utf-8");
//        return factory;
//    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate(clientHttpRequestFactory());
//    }
//
//    private ClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(Ints.checkedCast(TimeUnit.SECONDS.toMillis(90)));
//        factory.setConnectTimeout(Ints.checkedCast(TimeUnit.SECONDS.toMillis(90)));
//        return factory;
//    }
//
//
//    @Bean
//    public GroupController groupController(){
//        return new GroupController();
//    }

    @Bean
    public CISGroupService cisGroupService(){
        return new CISGroupService();
    }

    @Bean
    public CISDetailService cisDetailService(){
        return new CISDetailService();
    }

}
