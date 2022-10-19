package com.datmt.spring.springcsvtotable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/data")
public class ViewDataController {

    @GetMapping
    public String viewData() {
       return "view-data" ;
    }
}
