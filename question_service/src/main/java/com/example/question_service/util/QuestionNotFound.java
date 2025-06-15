package com.example.question_service.util;

import java.io.PrintStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class QuestionNotFound extends Throwable {
   private String message;
}
