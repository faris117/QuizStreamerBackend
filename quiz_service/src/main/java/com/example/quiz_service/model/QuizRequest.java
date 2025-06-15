package com.example.quiz_service.model;


import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizRequest {
    private UUID quizId;
    private String quizName;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime quizDate;
    private String description;
    private Boolean isPrivate;
    private String AuthorId;
    private QuizCategory category;
   
}
