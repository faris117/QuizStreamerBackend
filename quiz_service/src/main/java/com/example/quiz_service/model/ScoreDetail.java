package com.example.quiz_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScoreDetailsID.class)
public class ScoreDetail {
    @Id
    private  String contestantId;
    @Id
    private  String quizId;
    private  Float score_percentage;

}
