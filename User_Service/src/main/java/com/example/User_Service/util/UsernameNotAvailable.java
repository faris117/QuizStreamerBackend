package com.example.User_Service.util;

import lombok.AllArgsConstructor;



@AllArgsConstructor()
public class UsernameNotAvailable extends Throwable {
    private String errorMessage;
    
}
