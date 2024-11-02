package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClinicAttendeeDTO {
    private int id;
    private Date regDate;
    private String currentClinicId;
    private Long userId;                   // Reference to the User ID
    private List<Long> appointmentIds;     // List of Appointment IDs associated with the clinic attendee
}
