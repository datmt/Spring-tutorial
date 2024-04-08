package com.datmt.spring.springcameltutorial;


import org.slf4j.Logger;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

public class ExceptionHandler {

    public static record ExceptionResponse(String message) {
    }
    Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionHandler.class);
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        logger.info("Exception: {}", e.getMessage());
        return ResponseEntity.ok(new ExceptionResponse(e.getMessage()));
    }
}
