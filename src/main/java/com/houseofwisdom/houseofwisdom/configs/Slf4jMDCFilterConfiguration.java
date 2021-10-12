package com.houseofwisdom.houseofwisdom.configs;

import com.houseofwisdom.houseofwisdom.filters.Slf4jMDCFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
@ConfigurationProperties(prefix = "config.slf4jfilter")
public class Slf4jMDCFilterConfiguration {
    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String requestHeader = null;

    @Bean
    public FilterRegistrationBean serveletRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        final Slf4jMDCFilter slf4jMDCFilter = new Slf4jMDCFilter(responseHeader, mdcTokenKey, requestHeader);
        registrationBean.setFilter(slf4jMDCFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
