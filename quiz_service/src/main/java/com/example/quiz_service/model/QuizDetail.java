package com.example.quiz_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDetail {
    private String quizId;
    private String AuthorId;
}
