package com.example.quiz_service.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz_service.model.ScoreDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.service.QuizService;


@RestController
@RequestMapping("quiz/quizStreamer")
public class Quizcontroller {

    @Autowired
    private QuizService quizService;

    @GetMapping("/get")
    public ResponseEntity<List<Quiz>> getTodayQuizez(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate today){
          
            return ResponseEntity.ok().body(quizService.getQuizez(today));
    }
    @PostMapping("/saveScore")
    public ResponseEntity<String> saveScore(@RequestBody ScoreDetail scoreDetail){
        quizService.saveScore(scoreDetail);
        return ResponseEntity.ok().body("Score Saved");
    }
}
