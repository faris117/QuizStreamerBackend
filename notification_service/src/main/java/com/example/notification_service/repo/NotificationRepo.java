package com.example.notification_service.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notification_service.Model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,UUID> {

}
