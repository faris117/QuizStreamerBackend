package com.example.quiz_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.quiz_service.model.QuizRequest;
import com.example.quiz_service.model.QuizWrapper;
import com.example.quiz_service.service.QuizManagerService;
import com.example.quiz_service.util.QuizNotFound;
import com.example.quiz_service.util.UserNotPresent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/quiz/manageQuiz")
public class QuizManagerController {

    @Autowired
    private QuizManagerService quizService;

    @PostMapping("/createQuiz")
    public ResponseEntity<String> CreateQuiz(@RequestPart QuizRequest quizRequest,@RequestPart MultipartFile quizImage) throws UserNotPresent {
        quizService.createQuiz(quizRequest,quizImage);
        return ResponseEntity.ok().body("");
    }
    
    @GetMapping("/getQuiz")
    public ResponseEntity<QuizWrapper> getQuiz(@RequestParam String quizId) throws QuizNotFound {
        return ResponseEntity.ok().body(quizService.getQuiz(quizId));
    }
    
    @PutMapping("/updateQuiz")
    public ResponseEntity<String> updateQuiz(@RequestPart QuizRequest quizRequest,@RequestPart MultipartFile quizImage) throws UserNotPresent,QuizNotFound  {
        quizService.updateQuiz(quizRequest,quizImage);
      return ResponseEntity.ok().body("Quiz updated");
    }

    @DeleteMapping("/deleteQuiz")
    public ResponseEntity<String> deleteQuiz(@RequestParam("quizId") String quizId){
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok().body("Deleted");
    }

    @GetMapping("/verifyQuizDetail")
    public boolean verfyQuiz(@RequestParam String quizId) {
        return quizService.verfyQuizDetail(quizId);
    }
    
}
