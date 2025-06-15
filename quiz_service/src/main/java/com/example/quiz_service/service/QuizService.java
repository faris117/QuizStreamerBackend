package com.example.quiz_service.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import com.example.quiz_service.model.ScoreDetail;
import com.example.quiz_service.model.ScoreDetailsID;
import com.example.quiz_service.repo.ScoreDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.repo.QuizRepo;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private ScoreDetailsRepo scoreDetailsRepo;

    public List<Quiz> getQuizez(LocalDate today){
        LocalDateTime startDate=today.atStartOfDay();
        LocalDateTime endDate=startDate.plusDays(1);
        List<Quiz> quizez=quizRepo.findByQuizDateBetween(startDate,endDate);
        return quizez;
    }

    public void  saveScore(ScoreDetail scoreDetail) {
        ScoreDetailsID id = new ScoreDetailsID(scoreDetail.getContestantId(), scoreDetail.getQuizId());
        Optional<ScoreDetail> scoreDetails = scoreDetailsRepo.findById(id);
        if (scoreDetails.isEmpty()) {
            scoreDetailsRepo.save(scoreDetail);
        }

    }
}
