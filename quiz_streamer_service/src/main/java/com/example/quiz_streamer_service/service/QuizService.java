package com.example.quiz_streamer_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.quiz_streamer_service.model.*;
import com.example.quiz_streamer_service.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.quiz_streamer_service.repo.QuizRepo;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionClient questionClient;

    @Autowired
    private QuizClient quizClient;

    @Scheduled(fixedRate = 1000*60*60*3)
    @Cacheable
    public void updateLiveQuiz(){
      //  Optional<List<Quiz>> quizez=quizRepo;
    }

    public Boolean joinQuiz(QuizJoinRequest request) throws QuizNotFound {
        Optional<Quiz> quiz =  quizRepo.findById(UUID.fromString(request.getQuizId()));
        if(quiz.isEmpty()){
             throw new QuizNotFound("QuizNotFound");
        }
        String AuthorId=quiz.get().getAuthorId();
        LocalDateTime dateTime=quiz.get().getQuizDate();
        LocalDateTime currentDateTime=LocalDateTime.now();
        if(dateTime.isAfter(currentDateTime) || dateTime.equals(currentDateTime)){
            if(!AuthorId.equals(request.getUserId())){
               return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
        return  Boolean.FALSE;
    }

    public List<Question> getQuestions(String quizId) throws QuestionNotFound {
       ResponseEntity<List<Question>> response= questionClient.getQuestionOfQuiz(quizId);
       if(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()){
           throw new QuestionNotFound("Questions Not Found");
       }
        return response.getBody();
    }

    //pending some more steps to update
    public Integer findResult(QuizAnswers answers) throws ResultNotFound {
        ResponseEntity<Integer> response=questionClient.getResult(answers);
        if(response.getStatusCode()!= HttpStatus.OK){
            throw new ResultNotFound("can't find Result");
        }
        Float score_percentage=(float)(response.getBody()/answers.getAnswers().size());
        ScoreDetail scoreDetail=new ScoreDetail(answers.getUserId(), answers.getUserId(),score_percentage );
        quizClient.saveScore(scoreDetail);
        return  response.getBody();
    }
}
