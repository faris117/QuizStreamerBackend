package com.example.User_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User_Service.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User,UUID> {
    public  Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
