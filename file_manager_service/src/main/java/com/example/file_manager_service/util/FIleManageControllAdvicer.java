package com.example.file_manager_service.util;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FIleManageControllAdvicer {

    @ExceptionHandler(ImageNotFound.class)
    public ResponseEntity<String> handleImageNotFound(){
        return ResponseEntity.badRequest().body("image Not Found");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOExceptions(){
        return ResponseEntity.badRequest().body("IO Exceptions");
    }
}
