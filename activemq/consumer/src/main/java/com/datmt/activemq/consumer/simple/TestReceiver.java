package com.datmt.activemq.consumer.simple;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestReceiver {

    @JmsListener(destination = "test_queue")
    public void getTest(String testMessage) {
        System.out.printf("Got message %s%n", testMessage);
    }
}
