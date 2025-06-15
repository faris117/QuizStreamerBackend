package com.example.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.question_service.model.Answer;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.QuizAnswers;
import com.example.question_service.util.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.question_service.model.Question;
import com.example.question_service.repository.QuestionRepo;
import com.example.question_service.util.FileMangerClient;
import com.example.question_service.util.QuestionNotFound;
import com.example.question_service.util.QuizServiceClient;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private FileMangerClient fileManager;

    @Autowired
    private QuizServiceClient quizServiceClient;
    @Autowired
    private QuestionMapper mapper;

    public void saveQuestion(Question question, MultipartFile questionFile) {
        if(!questionFile.isEmpty()){
           
           if(quizServiceClient.verfyQuiz(question.getQuizId())){
           ResponseEntity<String> response= fileManager.uploadFile(questionFile);
           if(response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
             question.setQuestionImage(response.getBody());
           }
           questionRepo.save(question);
        }
        }
        
    }

    public Question getQuestion(String questionId) throws QuestionNotFound {
        Optional<Question> question=questionRepo.findById(UUID.fromString(questionId));
        if(question.isEmpty()){
                throw new QuestionNotFound(questionId+" not found");
        }
        return question.get();

    }

    public void updateQuestion(Question question, MultipartFile questionFile) {
         if(!questionFile.isEmpty()){
            ResponseEntity<String> response= fileManager.updateImage(question.getQuestionImage(), questionFile);
            if(response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
                 question.setQuestionImage(response.getBody());
            }
        }
        questionRepo.save(question);

    }

    public void deleteQuestion(String questionId) {
     Optional<Question> question=questionRepo.findById(UUID.fromString(questionId));
      if(!question.isEmpty()){
            if(!question.get().getQuestionImage().isEmpty()){
              fileManager.deleteImage(question.get().getQuestionImage());
            }
            questionRepo.delete(question.get());
        }
    
    }

    public List<QuestionWrapper> getQuestionsOfQuiz(String quizId) throws QuestionNotFound {
        Optional<List<Question>> questions = questionRepo.findByQuizId(UUID.fromString(quizId));
        if(questions.isEmpty()){
            throw new QuestionNotFound("questions not found");
        }
        List<QuestionWrapper> questions1=new ArrayList<>();

        for(Question quest: questions.get()){
            QuestionWrapper wrapper= mapper.toQuestionWrapper(quest);
            questions1.add(wrapper);
        }
        return  questions1;
    }

    public Integer computeResult(QuizAnswers answers)  {
        int score=0;
        for(Answer ans:answers.getAnswers()){
            Optional<Question> question=questionRepo.findById(UUID.fromString(ans.getQuestionId()));
            if(question.isPresent() && question.get().getAnswer().equals(ans.getAnswer()) && question.get().getQuizId().equals(answers.getQuizId())){
                score=score+1;
            }

        }
        return score;
    }
}
