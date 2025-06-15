package com.example.quiz_service.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotPresent extends Throwable {
    private String ErrorMessage;
}
