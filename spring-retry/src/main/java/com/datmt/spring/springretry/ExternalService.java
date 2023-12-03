package com.datmt.spring.springretry;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    private final RetryTemplate retryTemplate;

    public ExternalService(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    public String getFromExternal() {
        try {
            retryTemplate.execute((context) -> {
                System.out.println("Retry count: " + context.getRetryCount());
                try {
                    Thread.sleep(6_000L);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                }
                throw new RuntimeException("Exception");
            });
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;


    }

}
