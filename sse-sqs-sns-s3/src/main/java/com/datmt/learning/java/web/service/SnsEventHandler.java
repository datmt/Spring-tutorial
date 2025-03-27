package com.datmt.learning.java.web.service;

import com.datmt.learning.java.web.controller.SseController;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Service
public class SnsEventHandler {
    private final SseController sseController;

    public SnsEventHandler(SseController sseController) {
        this.sseController = sseController;
    }

    // Simulate receiving an SNS event
    public void handleSnsEvent(String bucket, String key, String username) {
        String url = generatePresignedUrl(bucket, key);
        sseController.sendToUser(username, url);
    }

    private String generatePresignedUrl(String bucket, String key) {
        S3Presigner presigner = S3Presigner.builder()
                .credentialsProvider(ProfileCredentialsProvider.create("aws-demo"))
                .region(Region.US_EAST_1)
                .build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();

        return presigner.presignGetObject(presignRequest).url().toString();
    }
}

