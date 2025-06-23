package com.example.auth_service.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_service.model.UserCredential;

@Repository
public interface  UserCredentialRepo extends  JpaRepository<UserCredential, UUID> {
    Optional<UserCredential> findByUsername(String userName);
}
