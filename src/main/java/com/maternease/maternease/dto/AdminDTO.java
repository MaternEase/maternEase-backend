package com.maternease.maternease.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdminDTO {
    private int adminId;
    private String email;
    private List<NotificationDTO> notifications;
}
