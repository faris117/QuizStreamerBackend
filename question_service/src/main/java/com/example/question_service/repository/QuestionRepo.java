package com.example.question_service.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.question_service.model.Question;

@Repository
public interface QuestionRepo  extends JpaRepository<Question,UUID>{

    Optional<List<Question>> findByQuizId(UUID uuid);
}
