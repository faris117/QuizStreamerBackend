package com.example.User_Service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.User_Service.model.User;
import com.example.User_Service.model.UserWrapper;
import com.example.User_Service.repository.UserRepo;
import com.example.User_Service.util.FileMangerClient;
import com.example.User_Service.util.UsernameNotAvailable;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FileMangerClient fileManager;

    public void createUser(User user,MultipartFile profilePicFile) {

        String profilePic =fileManager.uploadFile(profilePicFile).getBody();
        if(!profilePic.equals(null)){
            user.setProfilePic(profilePic);
        }
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        userRepo.save(user);
    }

    //checking wheather the username is available for new user
    public boolean isUsernameAvailable(String username) {
        Optional<User> user=userRepo.findByUsername(username.toLowerCase());
        return user.isEmpty();
    }

    public void deleteUser(String username) {
        userRepo.findByUsername(username).ifPresent((user)->{userRepo.deleteById(user.getId());});

    }

    public void updateUser(User updates) {
        updates.setUsername(updates.getUsername().toLowerCase());
        updates.setEmail(updates.getEmail().toLowerCase());
        userRepo.save(updates);
    }

    public UserWrapper getUser(String username) throws UsernameNotAvailable  {
        Optional<User> user=userRepo.findByUsername(username);
        if(user.isEmpty()){
            System.out.println("user not available");
            throw new UsernameNotAvailable(username+" not found") ;
        }
        else {
            UserWrapper wrapper=new UserWrapper(user.get());
            ResponseEntity<ByteArrayResource> response=fileManager.getImage(user.get().getProfilePic());
            if(response.getStatusCode()==HttpStatus.OK){
                    wrapper.setProfilePic(response.getBody());
            }
            return wrapper;
        }
    }
    
    //checking wheather the email is available for new user
    public boolean isEmailAvailable(String email){
        Optional<User> user=userRepo.findByEmail(email.toLowerCase());
        return user.isEmpty();
    }

    public Boolean isUserPresent(String userId) {
         Optional<User> user=userRepo.findById(UUID.fromString(userId));
         return user.isPresent();
    }
}
