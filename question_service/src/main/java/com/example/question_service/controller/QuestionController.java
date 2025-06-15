package com.example.question_service.controller;

import com.example.question_service.model.Answer;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.QuizAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.question_service.model.Question;
import com.example.question_service.service.QuestionService;
import com.example.question_service.util.QuestionNotFound;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RestController
@RequestMapping("/question_service")
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @PostMapping("/Question")
    public ResponseEntity<String> createQuestion(@RequestPart Question question,@RequestPart MultipartFile questionFile){
        questionService.saveQuestion(question,questionFile);
        return ResponseEntity.ok().body("Created");
    }

    @GetMapping("/Question")
    public ResponseEntity<Question> getQuestion(@RequestParam String questionId) throws QuestionNotFound {
        return ResponseEntity.ok().body(questionService.getQuestion(questionId)) ;
    }

    @PutMapping("/Question")
    public ResponseEntity<String> updateQuestion(@RequestPart Question question,@RequestPart MultipartFile questionFile){
        questionService.updateQuestion(question,questionFile);
        return ResponseEntity.ok().body("Updated");
    }

    @DeleteMapping("/Question")
    public ResponseEntity<String> deleteQuestion(@RequestParam String questionId){
         questionService.deleteQuestion(questionId);
        return ResponseEntity.ok().body("Deleted");
    }

    @PostMapping("/Questions")
    public  ResponseEntity<List<QuestionWrapper>> getQuestionOfQuiz(@RequestParam String quizId) throws QuestionNotFound {
        List<QuestionWrapper> questions=questionService.getQuestionsOfQuiz(quizId);
        return  ResponseEntity.ok().body(questions);
    }

    @PostMapping("/getResult")
    public ResponseEntity<Integer> getResult(@RequestBody QuizAnswers answers){
        return ResponseEntity.ok().body( questionService.computeResult(answers));
    }

}
