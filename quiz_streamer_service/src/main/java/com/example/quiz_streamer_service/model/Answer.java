package com.example.quiz_streamer_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String questionId;
    private  String answer;
}
