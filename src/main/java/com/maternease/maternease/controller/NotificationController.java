package com.maternease.maternease.controller;

import com.maternease.maternease.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint to create a notification
    @PostMapping("/create")
    public String createNotification(
            @RequestParam String message,
            @RequestParam String userType,
            @RequestParam Long userId,
            @RequestParam String notificationType,
            @RequestParam(required = false) String email) {

        // Call the service method to create the notification
        notificationService.createNotification(message, userType, userId, notificationType, email);

        return "Notification created successfully!";
    }

    // You can add more endpoints as needed, e.g., for retrieving notifications
}
