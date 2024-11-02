package com.maternease.maternease.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class AppointmentDTO {
    private int appointmentNo;
    private Date appointmentDate;
    private Time time;
    private String NICofRegistrant;
    private Long clinicAttendeeId;
}
