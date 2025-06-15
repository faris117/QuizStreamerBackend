package com.example.User_Service.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvicer {
    @ExceptionHandler(UsernameNotAvailable.class)
    public ResponseEntity<String> handleUsernameNotAvailable(){
        return ResponseEntity.badRequest().body("UserName not available");
    }
}