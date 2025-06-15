package com.example.quiz_streamer_service.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuestionNotFound extends Throwable {
    private String errorMessage;
}
