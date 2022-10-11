package com.datmt.activemq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ProducerApplication.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        jmsTemplate.convertAndSend("test_queue", "Some message");
    }

}
