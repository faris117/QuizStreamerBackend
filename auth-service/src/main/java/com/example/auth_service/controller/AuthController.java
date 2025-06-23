package com.example.auth_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.model.UserCredential;
import com.example.auth_service.service.AuthService;
import com.example.auth_service.model.AuthRequest;
import com.example.auth_service.model.TokenValidateRequest;
import com.example.auth_service.service.JwtService;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/hello")
    public String helloworld(){
        return "helloworld";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredential userCredential)
    {
        authService.saveUser(userCredential);
        return ResponseEntity.ok().body("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest)
    {
        System.out.println(authRequest.getUsername()+" "+authRequest.getPassword());
        try {
            Authentication auth=authService.authenticate(authRequest);
            if(auth.isAuthenticated()){
            
            String token=jwtService.createToken(authRequest.getUsername());
             return ResponseEntity.ok().body(token);
           }else{
            return ResponseEntity.badRequest().body("login failed");
           }
        } catch (Exception e) {
            System.out.println(e);

            return ResponseEntity.badRequest().body("login failed");
           
        }
           
           
            
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestBody TokenValidateRequest tokenReq ) {
        
        try{
            System.out.println(tokenReq);
            boolean tokenValidity=jwtService.validateToken(tokenReq.getToken());
        System.out.println(tokenValidity);
        if(tokenValidity){
             return ResponseEntity.ok().body("");
        }
        return ResponseEntity.badRequest().body("invalid token");
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("invalid due to catch");
        }
        
    }
    
}
