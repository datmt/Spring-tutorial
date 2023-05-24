package com.datmt.spring.springexceptionhandling.service;

import com.datmt.spring.springexceptionhandling.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import com.datmt.spring.springexceptionhandling.model.User;

import java.util.List;

@Service
public class UserService {

    List<User> users = List.of(new User("Jane", "jane@a.com"),
            new User("John", "john@b.com"), new User("Jack", "jack@j.com"));
   public User findByName(String name) {
        var user = users.stream().filter(u -> u.getName().equals(name)).findFirst();
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException("User not found");
   }

   public User findByEmail(String email) {
        var user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException("User not found");
   }
}
