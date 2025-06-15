package com.example.quiz_streamer_service.controller;

import com.example.quiz_streamer_service.model.Question;

import com.example.quiz_streamer_service.model.QuizAnswers;
import com.example.quiz_streamer_service.model.QuizJoinRequest;
import com.example.quiz_streamer_service.service.QuizService;
import com.example.quiz_streamer_service.util.QuestionNotFound;
import com.example.quiz_streamer_service.util.ResultNotFound;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.quiz_streamer_service.util.QuizNotFound;

import java.util.List;

@RestController
@RequestMapping("/quiz/joinQuiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    
    @PostMapping("/join")
    public ResponseEntity<Boolean> joinQuiz(@RequestBody QuizJoinRequest request) throws QuizNotFound{
           Boolean canJoin=  quizService.joinQuiz(request);
        return ResponseEntity.ok().body(canJoin) ;
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<Question>> getQuestions(@RequestParam String quizId) throws QuestionNotFound {
        List<Question> questions=quizService.getQuestions(quizId);
        return ResponseEntity.ok().body(questions);

    }   
    
    @PostMapping("/getResult")
    public ResponseEntity<Integer> getResult(@RequestBody QuizAnswers answers) throws ResultNotFound {

        return ResponseEntity.ok().body(quizService.findResult(answers));
    }
    
}
