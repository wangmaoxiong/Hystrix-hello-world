package com.wmx.gatewayzuul.config;

import com.wmx.gatewayzuul.filter.QueryParamPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangmaoxiong
 */
@Configuration
public class BeanConfig {

    @Bean
    public QueryParamPreFilter queryParamPreFilter() {
        return new QueryParamPreFilter();
    }
}
