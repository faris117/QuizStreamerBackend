package com.example.auth_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.auth_service.model.UserCredential;
import com.example.auth_service.repo.UserCredentialRepo;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepo userCredentialRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> usercredOptional=userCredentialRepo.findByUsername(username);
        return  usercredOptional.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }

}
