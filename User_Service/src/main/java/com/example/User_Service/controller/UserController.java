package com.example.User_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.User_Service.model.User;
import com.example.User_Service.model.UserWrapper;
import com.example.User_Service.service.UserService;
import com.example.User_Service.util.UsernameNotAvailable;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;





@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;


    //create user
    @PostMapping("/createUser")
    public ResponseEntity<String> CreateUser(@RequestPart User user,@RequestPart MultipartFile profilePicFile) throws UsernameNotAvailable{

        if (!userService.isUsernameAvailable(user.getUsername())) {
            System.out.println("username Not available");
       throw new UsernameNotAvailable(user.getUsername()+"Not available");
        }
        userService.createUser(user,profilePicFile);
        return ResponseEntity.ok("User created");
    }

    //deleting user
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String username){
        userService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    //Get request of user
    @GetMapping("/getUser/{username}")
    public ResponseEntity<UserWrapper> getUser(@PathVariable String username) throws UsernameNotAvailable{
        UserWrapper user=userService.getUser(username);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    //Updating User
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUsername(@RequestBody User updates)
    {
        userService.updateUser(updates);
        return ResponseEntity.status(HttpStatus.OK).body("updated User");
    }
    
    @GetMapping("/isUserNameAvailable/{username}")
    public ResponseEntity<Boolean> isUserNameAvailable(@PathVariable String username) {
        return ResponseEntity.ok()
                .body(userService.isUsernameAvailable(username));
    }
    
    @GetMapping("/isEmailAvailable/{email}")
    public ResponseEntity<Boolean> isEmailAvailable(@PathVariable String email) {
        return ResponseEntity.ok()
                .body(userService.isEmailAvailable(email));
    }

    @GetMapping("/isUserAvailable")
    public ResponseEntity<Boolean> isUserPresent(@RequestParam String userId) {
        return ResponseEntity.ok().body(userService.isUserPresent(userId));
    }
    
    

}