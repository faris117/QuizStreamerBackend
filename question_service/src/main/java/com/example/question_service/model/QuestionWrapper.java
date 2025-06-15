package com.example.question_service.model;

import java.util.List;


import org.springframework.core.io.ByteArrayResource;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class QuestionWrapper {
   
    private String questionId;
   
    private String quizId;
    
    private String questionText;
  
    private List<String> options;
   
    
    private ByteArrayResource questionImage;


}
