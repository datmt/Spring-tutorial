package com.datmt.spring.springexceptionhandling.controller;

import com.datmt.spring.springexceptionhandling.model.User;
import com.datmt.spring.springexceptionhandling.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/find/{name}")
    public User findByName(@PathVariable  String name) {
        return userService.findByName(name);
    }
    @GetMapping("/find-by-email/{email}")
    public User findByEmail(@PathVariable @Email @Valid String email) {
        return userService.findByEmail(email);
    }

}
