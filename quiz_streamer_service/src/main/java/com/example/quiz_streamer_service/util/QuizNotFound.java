package com.example.quiz_streamer_service.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuizNotFound extends Throwable {
private String errorMessage;
}
