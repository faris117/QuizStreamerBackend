package com.example.quiz_streamer_service.util;

import com.example.quiz_streamer_service.model.Quiz;
import com.example.quiz_streamer_service.model.ScoreDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient("quiz-service")
public interface QuizClient {
    @GetMapping("/quiz/quizStreamer/get")
     ResponseEntity<List<Quiz>> getTodayQuizez(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate today);

    @PostMapping("/quiz/quizStreamer/saveScore")
     ResponseEntity<String> saveScore(@RequestBody ScoreDetail scoreDetail);
}
