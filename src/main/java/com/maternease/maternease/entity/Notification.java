package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="notification")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationNo;

    private String message;
    private Date date;
    private Time time;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
