package com.datmt.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    JmsTemplate template;
    @GetMapping("/send")
    public void sendMessage() {
       template.convertAndSend("test_queue", "Hello random string " + UUID.randomUUID());
    }
}
