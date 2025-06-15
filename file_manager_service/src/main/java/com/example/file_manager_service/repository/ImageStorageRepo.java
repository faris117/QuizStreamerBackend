package com.example.file_manager_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.file_manager_service.model.ImageStorage;

@Repository
public interface ImageStorageRepo extends JpaRepository<ImageStorage,UUID> {

}
