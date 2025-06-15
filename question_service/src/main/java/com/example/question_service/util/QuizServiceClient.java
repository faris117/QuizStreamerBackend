package com.example.question_service.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("quiz-service")
public interface QuizServiceClient {

      @GetMapping("/verifyQuizDetail")
    public boolean verfyQuiz(@RequestParam String quizId) ;
}
