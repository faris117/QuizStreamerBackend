package com.example.quiz_service.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.QuizRequest;
import com.example.quiz_service.model.QuizWrapper;
import com.example.quiz_service.repo.QuizRepo;
import com.example.quiz_service.util.FileMangerClient;
import com.example.quiz_service.util.QuizNotFound;
import com.example.quiz_service.util.UserNotPresent;
import com.example.quiz_service.util.UserServiceClient;

@Service
public class QuizManagerService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private FileMangerClient fileManager;

    public void createQuiz(QuizRequest quizRequest,MultipartFile quizImage) throws UserNotPresent{
        Boolean isUserPresent=userServiceClient.isUserPresent(quizRequest.getAuthorId()).getBody();
        if(isUserPresent){
                 Quiz quiz=Quiz.builder()
                            .quizName(quizRequest.getQuizName())
                            .AuthorId(quizRequest.getAuthorId())
                            .category(quizRequest.getCategory())
                            .description(quizRequest.getDescription())
                            .isPrivate(quizRequest.getIsPrivate())
                            .quizDate(quizRequest.getQuizDate())
                            .build();
        ResponseEntity<String> response=fileManager.uploadFile(quizImage);
        if(response.getStatusCode()==HttpStatus.OK){
            quiz.setPoster(response.getBody());
        }
        
        
       quizRepo.save(quiz);
        }else{
            throw new UserNotPresent("User Not Present");
        }
    }

    

    public QuizWrapper getQuiz(String quizId) throws QuizNotFound {
       Optional<Quiz> quiz= quizRepo.findById(UUID.fromString(quizId));
       if(quiz.isEmpty()){
        throw new QuizNotFound("Quiz not found");
       }
       QuizWrapper wrapper=new QuizWrapper(quiz.get());
       ResponseEntity<ByteArrayResource> response=fileManager.getImage(quiz.get().getPoster());
       if(response.getStatusCode()==HttpStatus.OK){
        wrapper.setPoster(response.getBody());
       }
       return wrapper;
    }

    public void updateQuiz(QuizRequest quizRequest,MultipartFile quizImage) throws UserNotPresent,QuizNotFound {
        Boolean isUserPresent=userServiceClient.isUserPresent(quizRequest.getAuthorId()).getBody();
        if(isUserPresent){

            Optional<Quiz>quizData=quizRepo.findById(quizRequest.getQuizId());
            if(quizData.isPresent()){
                 Quiz quiz=Quiz.builder()
                            .quizName(quizRequest.getQuizName())
                            .AuthorId(quizRequest.getAuthorId())
                            .category(quizRequest.getCategory())
                            .description(quizRequest.getDescription())
                            .isPrivate(quizRequest.getIsPrivate())
                            .quizDate(quizRequest.getQuizDate())
                            .build();
                if(quizImage!=null){
                    ResponseEntity<String> response=fileManager.updateImage(quizData.get().getPoster(), quizImage);

                }
                
                quizRepo.save(quiz);
            }else{
                throw new QuizNotFound("quiz not found");}
            }
            else{
            throw new UserNotPresent("User Not Present");}
    }

    public void deleteQuiz(String quizId) {
        Quiz quiz=quizRepo.findById(UUID.fromString(quizId)).get();
        fileManager.deleteImage(quiz.getPoster());
        quizRepo.deleteById(UUID.fromString(quizId));
    }



    public boolean verfyQuizDetail(String quizId) {
        Optional<Quiz> quiz=quizRepo.findById(UUID.fromString(quizId));
        if(quiz.isPresent()){
            return true;
        }
        return false;
    }


}
