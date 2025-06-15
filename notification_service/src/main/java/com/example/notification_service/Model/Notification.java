package com.example.notification_service.Model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Notification {

    @Id
    private UUID notificationId;
    private String  userId;
    private String message;
    private NotificationStatus status; 
}
