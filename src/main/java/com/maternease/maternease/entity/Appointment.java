package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentNo;
    private Date appointmentDate;
    private Time time;
    private String NICofRegistrant;

    @ManyToOne
    @JoinColumn(name = "clinic_attendee_id")
    private ClinicAttendee clinicAttendee;

    @ManyToOne
    @JoinColumn(name = "midwife_id")
    private Midwife midwife;
}
