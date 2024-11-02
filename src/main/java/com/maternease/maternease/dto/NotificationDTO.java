package com.maternease.maternease.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private int notificationNo;
    private String message;
    private String date;
    private String time;
}
