package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.entity.Notification;
import com.maternease.maternease.repository.NotificationRepo;
import com.maternease.maternease.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;

@Service
public class NotificationServiceIMPL implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void createNotification(String message, String userType, Long userId, String notificationType, String email) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDate(new Date());
        notification.setTime(new Time(System.currentTimeMillis()));
        notification.setUserType(userType);
        notification.setIsRead(false);
        notification.setNotificationType(notificationType);

        // Link to the user
        // Assume you have a user lookup method (e.g., userRepo.findById(userId))
        // notification.setUser(user);

        notificationRepo.save(notification);

        // If the notification type is email, send an email
        if ("Email".equalsIgnoreCase(notificationType)) {
            sendEmail(email, "Notification", message);
        }
    }

    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
