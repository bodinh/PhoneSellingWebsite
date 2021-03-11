package com.example.demo.MyFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {
    @Bean
    public FilterRegistrationBean<FilterAdmin> loggingFilter() {
        FilterRegistrationBean<FilterAdmin> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new FilterAdmin());
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<FilterUser> loggingUser() {
        FilterRegistrationBean<FilterUser> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new FilterUser());
        registrationBean.addUrlPatterns("/cart/*");
        return registrationBean;
    }
}
