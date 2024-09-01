package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="clinic_attendee")
@Data
public class ClinicAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date regDate;
    private String currentClinicId;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic; // Add this field to link back to the Clinic entity

    @ManyToOne
    @JoinColumn(name = "user_id")
    private OurUsers ourUsers;

    @OneToMany(mappedBy = "clinicAttendee")
    private List<Appointment> appointments;
}
