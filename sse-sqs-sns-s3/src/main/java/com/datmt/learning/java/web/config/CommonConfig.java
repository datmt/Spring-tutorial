package com.datmt.learning.java.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@org.springframework.context.annotation.Configuration
@EnableAsync
public class CommonConfig {
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("us-east-1"))
                .credentialsProvider(ProfileCredentialsProvider.create("aws-demo"))
                .build();
    }
//    @Bean
//    public Executor taskExecutor() {
//        return Executors.newSingleThreadExecutor();
//    }
}
