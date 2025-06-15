package com.example.User_Service.util;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("file-manager-service")
public interface  FileMangerClient {
    @PostMapping("/files/uploadFile")
    ResponseEntity<String> uploadFile(@RequestParam("imageFile") MultipartFile imageFile );

    @GetMapping("/files/getFile")
     ResponseEntity<ByteArrayResource> getImage(@RequestParam("imageId") String imageStorageId);

    @PutMapping("/files/updateImage")
     ResponseEntity<String> updateImage(@RequestParam("imageId") String imageId, @RequestPart MultipartFile image);
    @DeleteMapping("/files/deleteImage")
    ResponseEntity<String> deleteImage(@RequestParam("imageId") String imageId);
}