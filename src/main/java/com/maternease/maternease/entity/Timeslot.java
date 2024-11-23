package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="timeslot")
@Data
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeslotId;

    private String time; // Example: "10:00 AM - 11:00 AM"

    @ManyToOne
    @JoinColumn(name = "clinic_sch_id")
    private ClinicSch clinicSch;


    @OneToMany(mappedBy = "timeslot", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    // Getters and Setters
}
