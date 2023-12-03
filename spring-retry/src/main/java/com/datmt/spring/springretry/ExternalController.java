package com.datmt.spring.springretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
public class ExternalController {

    @Autowired
    private ExternalService externalService;

    @GetMapping
    public String get() {
        var start = System.currentTimeMillis();
        var result = externalService.getFromExternal();

        System.out.println("Time: " + (System.currentTimeMillis() - start));
        return result;
    }
}
