package com.example.file_manager_service.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.file_manager_service.model.ImageStorage;
import com.example.file_manager_service.repository.ImageStorageRepo;
import com.example.file_manager_service.util.ImageNotFound;

@Service
public class ImageStorageService {
    
    @Autowired
    private ImageStorageRepo imageStorageRepo;
    
    public ImageStorage saveFile( MultipartFile imageFile) throws IOException {
        ImageStorage imageStorage=new ImageStorage();
        imageStorage.setImageName(imageFile.getOriginalFilename());
        imageStorage.setImageType(imageFile.getContentType());
        imageStorage.setImageData(imageFile.getBytes());
        return imageStorageRepo.save(imageStorage);
    }
    public ResponseEntity<ByteArrayResource> getImage(String imageStorageId) throws ImageNotFound {
       Optional<ImageStorage> imageFile=  imageStorageRepo.findById(UUID.fromString(imageStorageId));
       if(!imageFile.isPresent()){
        throw new ImageNotFound("NotFound");
       }
       ImageStorage image=imageFile.get();
       return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getImageType()))
                .body(new ByteArrayResource(image.getImageData())) ;
    }


    public void updateImage(String imageId, MultipartFile image) throws IOException,ImageNotFound {
        Optional<ImageStorage> imagefile=imageStorageRepo.findById(UUID.fromString(imageId));
        if(imagefile.isEmpty()){
            throw new ImageNotFound("NotFound");
        }
        ImageStorage imageStorage=imagefile.get();
        imageStorage.setImageName(image.getOriginalFilename());
        imageStorage.setImageType(image.getContentType());
        imageStorage.setImageData(image.getBytes());
        imageStorageRepo.save(imageStorage);
    }
    public void deleteImage(String imageId) {
       imageStorageRepo.deleteById(UUID.fromString(imageId));
    }

}
