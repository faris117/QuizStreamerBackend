package com.example.quiz_streamer_service.model;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDetail {
    @Id
    private  String contestantId;
    @Id
    private  String quizId;
    private  Float score_percentage;

}
