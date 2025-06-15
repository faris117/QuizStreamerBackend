package com.example.quiz_service.repo;

import com.example.quiz_service.model.ScoreDetail;
import com.example.quiz_service.model.ScoreDetailsID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreDetailsRepo  extends JpaRepository<ScoreDetail, ScoreDetailsID> {
}
