package com.example.quiz_streamer_service.model;

import lombok.Data;
@Data
public class QuizJoinRequest {
    
    private String userId;
    private String quizId;

}
