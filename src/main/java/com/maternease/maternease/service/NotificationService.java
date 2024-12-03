package com.maternease.maternease.service;

public interface NotificationService {
    void createNotification(String message, String userType, Long userId, String notificationType, String email);
}
