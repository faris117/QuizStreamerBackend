package com.example.User_Service.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {

    private UUID Id;
   
    private String username;
    
    private String email;
  
  ;
    private String firstname;
    private String lastname;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy",timezone = "UTC")
    private LocalDate dateOfBirth;
    private ByteArrayResource profilePic;

    public UserWrapper(User user){
        this.Id=user.getId();
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.firstname=user.getFirstname();
        this.lastname=user.getLastname();
        this.dateOfBirth=user.getDateOfBirth();
    }
}
