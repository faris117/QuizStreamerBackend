package com.example.quiz_streamer_service.repo ;

import java.util.UUID;
import com.example.quiz_streamer_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepo extends JpaRepository<Quiz,UUID> {

}
