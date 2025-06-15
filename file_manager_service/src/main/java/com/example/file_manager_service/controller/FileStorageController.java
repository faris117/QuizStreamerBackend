package com.example.file_manager_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.file_manager_service.service.ImageStorageService;
import com.example.file_manager_service.util.ImageNotFound;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/files")
public class FileStorageController {
    
    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("imageFile") MultipartFile imageFile ) throws IOException {
       return imageStorageService
                .saveFile(imageFile)
                .getImageStorageId()
                .toString();
         
    }
    
    @GetMapping("/getFile")
    public ResponseEntity<ByteArrayResource> getMethodName(@RequestParam("imageId") String imageStorageId) throws ImageNotFound {
        return imageStorageService.getImage(imageStorageId) ;
    }

    @PutMapping("/updateImage")
    public ResponseEntity<String> updateImage(@RequestParam("imageId") String imageId, @RequestPart MultipartFile image) throws IOException, ImageNotFound {
        imageStorageService.updateImage(imageId,image);
        return ResponseEntity.ok().body("Updated");
    }
    
    @DeleteMapping("/deleteImage")
    public ResponseEntity<String> deleteImage(@RequestParam("imageId") String imageId){
        imageStorageService.deleteImage(imageId);
        return ResponseEntity.ok().body("Deleted");
    }
}
