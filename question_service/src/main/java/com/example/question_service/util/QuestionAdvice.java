package com.example.question_service.util;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuestionAdvice {

    @ExceptionHandler(QuestionNotFound.class)
    public ResponseEntity<String> handleQuestionNotFound(QuestionNotFound questionNotFound){
        return ResponseEntity.badRequest().body("Error message"+questionNotFound.getMessage());
    }
}
