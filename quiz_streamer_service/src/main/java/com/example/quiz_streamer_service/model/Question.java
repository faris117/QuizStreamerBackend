package com.example.quiz_streamer_service.model;

import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public class Question {
    private String questionId;

    private String quizId;

    private String questionText;

    private List<String> options;


    private ByteArrayResource questionImage;
}
