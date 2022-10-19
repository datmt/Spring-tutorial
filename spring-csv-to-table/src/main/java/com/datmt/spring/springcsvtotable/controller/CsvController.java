package com.datmt.spring.springcsvtotable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class CsvController {

    @GetMapping
    public String uploadScreen() {
        return "upload-csv";
    }


    @PostMapping
    public String uploadFile(@RequestParam("files") MultipartFile[] files, Model modal) {

        for (MultipartFile file : files) {

        }
       return "upload-csv" ;
    }
}
