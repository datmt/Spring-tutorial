package com.datmt.tools.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Locale;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {

        this.messageSource = messageSource;
    }


    @GetMapping
    public String hello(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);
    }
}
