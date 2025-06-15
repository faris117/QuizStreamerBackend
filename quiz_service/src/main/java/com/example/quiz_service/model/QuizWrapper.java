package com.example.quiz_service.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;

@Data
public class QuizWrapper {
     private UUID quizId;
   
    private String quizName;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime quizDate;
    private String description;
    
    private Boolean isPrivate;

    private String AuthorId;
   
    private QuizCategory category;
    private ByteArrayResource poster;

    public QuizWrapper(Quiz quiz){
        this.quizId=quiz.getQuizId();
        this.quizName=quiz.getQuizName();
        this.quizDate=quiz.getQuizDate();
        this.description=quiz.getDescription();
        this.isPrivate=quiz.getIsPrivate();
        this.AuthorId=quiz.getAuthorId();
        this.category=quiz.getCategory();
    
    }
}
