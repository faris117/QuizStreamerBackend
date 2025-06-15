package com.example.quiz_streamer_service.util;

import com.example.quiz_streamer_service.model.Question;
import com.example.quiz_streamer_service.model.QuizAnswers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("question-service")
public interface QuestionClient {

    @PostMapping("/question_service/Questions")
    ResponseEntity<List<Question>> getQuestionOfQuiz(@RequestParam String quizId);

    @PostMapping("/getResult")
     ResponseEntity<Integer> getResult(@RequestBody QuizAnswers answers);

}
