package com.example.auth_service.service;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth_service.model.UserCredential;

public class CustomUserDetails implements UserDetails {

    private final UserCredential userCredential;
    public CustomUserDetails(UserCredential userCredential){
        this.userCredential=userCredential;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return userCredential.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
       return userCredential.getPassword();
    }

    @Override
    public String getUsername() {
       return  userCredential.getUsername();
    }

      @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

}
