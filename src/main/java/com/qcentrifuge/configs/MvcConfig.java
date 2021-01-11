package com.qcentrifuge.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    /*Добавление обычных отображаемых страниц без доп. действ*/
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin/panel").setViewName("apanel");
        registry.addViewController("/reg").setViewName("reg");
        registry.addViewController("/").setViewName("index");
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/upload/**").
                addResourceLocations("file:upload/");
    }


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
