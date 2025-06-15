package com.example.quiz_service.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceClient {

      @GetMapping("/User/isUserAvailable")
      ResponseEntity<Boolean> isUserPresent(@RequestParam("userId") String userId);
}
