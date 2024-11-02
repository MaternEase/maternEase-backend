package com.maternease.maternease.dto;

import java.util.List;

public class DoctorDTO {
    private int docId;
    private Long clinicId;              // Reference to the Clinic ID
    private List<Long> notificationIds; // List of Notification IDs associated with the doctor
}
