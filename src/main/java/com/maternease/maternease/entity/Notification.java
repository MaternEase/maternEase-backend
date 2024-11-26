package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "notification")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationNo;

    private String message;
    private Date date;
    private Time time;
    private String userType; // e.g., "Admin", "Midwife", "Doctor"
    private Boolean isRead; // Mark as read/unread

    @ManyToOne
    @JoinColumn(name = "user_id")
    private OurUsers user; // Refers to the recipient user

    private String notificationType; // e.g., "System", "Email"
}
