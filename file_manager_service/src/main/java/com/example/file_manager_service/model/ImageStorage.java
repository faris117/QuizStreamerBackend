package com.example.file_manager_service.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
@Entity
public class ImageStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageStorageId;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
