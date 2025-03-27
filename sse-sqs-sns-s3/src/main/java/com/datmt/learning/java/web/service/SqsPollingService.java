package com.datmt.learning.java.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class SqsPollingService {
    private final SnsEventHandler snsEventHandler;
    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsPollingService(SnsEventHandler snsEventHandler) {
        this.snsEventHandler = snsEventHandler;

        this.sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create("aws-demo"))
                .build();

        this.queueUrl = sqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                .queueName("s3-event-download-queue")
                .build()).queueUrl();

    }

    @PostConstruct
    void init() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this::startPolling);
    }
    public void startPolling() {
        while (true) {
            ReceiveMessageResponse response = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .waitTimeSeconds(20) // long polling
                    .maxNumberOfMessages(5)
                    .build());

            for (Message message : response.messages()) {
                handleMessage(message);
                sqsClient.deleteMessage(DeleteMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .receiptHandle(message.receiptHandle())
                        .build());
            }
        }
    }

    private void handleMessage(Message message) {
        log.info("Received message: {}", message.body());
        try {
            // SNS wraps your message in an outer envelope
            ObjectMapper mapper = new ObjectMapper();
            JsonNode outer = mapper.readTree(message.body());
            String innerMessage = outer.get("Message").asText();

            JsonNode payload = mapper.readTree(innerMessage);
            String s3Key = payload.get("s3Key").asText();
            String username = payload.get("username").asText();

            snsEventHandler.handleSnsEvent("cost-report-test-001", s3Key, username);

        } catch (Exception e) {
            log.error("Failed to handle message", e);
        }
    }
}

