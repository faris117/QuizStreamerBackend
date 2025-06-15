package com.example.quiz_service.util;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuizControllerAdvicer {

    @ExceptionHandler(UserNotPresent.class)
    public ResponseEntity<String> handleUserNotFound(){
        return ResponseEntity.badRequest().body("User Not Found");
    }
    
    @ExceptionHandler(QuizNotFound.class)
    public ResponseEntity<String> handleQuizNotFound(){
        return  ResponseEntity.badRequest().body("Quiz Not Found");
    }

}
