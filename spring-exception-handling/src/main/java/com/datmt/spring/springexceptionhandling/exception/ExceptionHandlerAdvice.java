package com.datmt.spring.springexceptionhandling.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request) {
        var body = new LinkedHashMap<String, Object>();
        body.put("message", ex.getMessage());
        body.put("extraMessage", "We are sorry");

        return ResponseEntity.badRequest().body(body);
    }
}
