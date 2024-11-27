package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private Mother mother;

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    @Column(name = "clinic_type")
    private String clinicType;

    // Getters and Setters
}

