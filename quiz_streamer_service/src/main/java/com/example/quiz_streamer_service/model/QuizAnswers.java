package com.example.quiz_streamer_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuizAnswers {
    private String quizId;
    private  String userId;
    private List<Answer> answers;
}
