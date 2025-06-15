package com.example.quiz_service.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz_service.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,UUID> {
    
    List<Quiz> findByQuizDateBetween(
        LocalDateTime start,
        LocalDateTime end);

}
