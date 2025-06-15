package com.example.question_service.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    @Autowired
    private FileMangerClient fileManager;
    public QuestionWrapper toQuestionWrapper(Question question){
        return QuestionWrapper.builder()
                        .questionId(question.getQuestionId().toString())
                        .quizId(question.getQuizId())
                        .questionText(question.getQuestionText())
                        .options(question.getOptions())
                        .questionImage(getFile(question.getQuestionImage()))
                        .build();
      

    }
    private ByteArrayResource getFile(String imageId){
        ResponseEntity<ByteArrayResource> response=  fileManager.getImage(imageId);
        if(response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return response.getBody();
        }
        return null;
    }
}
