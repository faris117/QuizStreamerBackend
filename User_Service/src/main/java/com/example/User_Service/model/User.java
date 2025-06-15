package com.example.User_Service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;


@Data()
@NoArgsConstructor()
@Entity()
@Table(name = "users")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    @Column(unique = true,nullable = false)
    private String username;
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstname;
    private String lastname;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy",timezone = "UTC")
    private LocalDate dateOfBirth;
    private String profilePic;
}
