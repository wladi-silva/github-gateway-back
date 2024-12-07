package com.wladi.githubgateway.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignConfiguration {

    @Value("${github.token}")
    String githubToken;

    @Bean
    RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = String.format("Bearer %s", githubToken);
            requestTemplate.header("Authorization", token);
        };
    }

}
