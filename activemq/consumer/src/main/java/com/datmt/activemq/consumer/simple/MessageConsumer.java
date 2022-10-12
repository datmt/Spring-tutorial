package com.datmt.activemq.consumer.simple;

import com.datmt.activemq.data.Letter;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "test_queue")
    public void getStringMessage(String testMessage) {
        System.out.printf("Got message %s%n", testMessage);
    }

    @JmsListener(destination = "test_letter")
    public void getTest(Letter letter) {
        System.out.printf("From %s to %s with content %s", letter.getSender(), letter.getReceiver(), letter.getContent());
    }
}
