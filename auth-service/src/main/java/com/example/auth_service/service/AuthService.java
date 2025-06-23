package com.example.auth_service.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.model.AuthRequest;
import com.example.auth_service.model.UserCredential;
import com.example.auth_service.repo.UserCredentialRepo;

@Service
public class AuthService {


    @Autowired
    private  UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserCredentialRepo userCredentialRepo;

    @Autowired
    private  PasswordEncoder passwordEncoder;



    public void saveUser(UserCredential userCredential) {
        try{
            var roles=userCredential.getRoles();
        if((roles.contains("ROLE_USER") || roles.contains("ROLE_CONTESTANT")) && !roles.contains("ROLE_ADMIN") ){
            userCredential.setCreatedAt(new Date());
            userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
            System.out.println(userCredential.getPassword());
            userCredentialRepo.save(userCredential);
        }
       
        }catch(Exception e){

               System.out.print(e);
        }
    }

    public Authentication authenticate(AuthRequest authRequest) {

       return authManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    }

}
