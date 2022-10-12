package com.datmt.activemq.producer;

import com.datmt.activemq.data.Letter;
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
    public String sendMessage() {
        var message = "Hello random string " + UUID.randomUUID();
        template.convertAndSend("test_queue", message);
        return message;
    }


    @GetMapping("/send-letter")
    public String sendLetter() {
        var letter = new Letter("Hello " + UUID.randomUUID(), "Jack", "Jill");
        template.convertAndSend("test_letter", letter);
        return "Letter sent!";
    }
}
