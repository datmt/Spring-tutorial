package com.datmt.spring.springcameltutorial.controller;

import com.datmt.spring.springcameltutorial.service.DivisionService;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final DivisionService divisionService;
    private final ProducerTemplate producerTemplate;

    public TestController(DivisionService divisionService, ProducerTemplate producerTemplate) {
        this.divisionService = divisionService;
        this.producerTemplate = producerTemplate;
    }

    @GetMapping("/{number}")
    public String divide(@PathVariable int number) {
        return String.valueOf(divisionService.divide(number));
    }

    @GetMapping("/camel/{number}")
    public String divideCamel(@PathVariable int number) {
        producerTemplate.start();
        var exchange1 = producerTemplate.send("direct:divide", exchange -> {
            exchange.getIn().setBody(number);
        });

        return exchange1.getMessage().getBody(String.class);
    }
}
