package com.example.question_service.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Question {
    @Id
    private UUID questionId;
    @Column(nullable=false)
    private String quizId;
    @Column(nullable=false)
    private String questionText;
    @Column(nullable=false)
    private List<String> options;
    @Column(nullable=false)
    private String answer;
    @Column(nullable=true)
    private String questionImage;
}

