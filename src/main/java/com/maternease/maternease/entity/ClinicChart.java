package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClinicChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dayOfWeek;
    private String recurringPattern;
    private String type;
    private String remarks; // optional notes or comments

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic; // Clinic related to the chart


}
