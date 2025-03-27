package com.datmt.learning.java.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "home";
    }
}
