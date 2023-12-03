package com.datmt.spring.springretry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean
    public RetryTemplate retryTemplate() {

        return RetryTemplate.builder()
//                .maxAttempts(3)
                .withTimeout(Duration.ofMillis(5_000L))
                .build();
    }
}
