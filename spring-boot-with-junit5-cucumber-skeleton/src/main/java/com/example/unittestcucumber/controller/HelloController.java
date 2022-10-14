package com.example.unittestcucumber.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hey you";
    }


    @GetMapping("/{name}")
    public String helloWithName(@PathVariable String name) {
       return "Hello, " + name;
    }
}
