package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "immunization" )
@Data
public class Immunization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary key


//    @Column(name = "vaccine_type", nullable = true)
//    private String vaccineType;
//    private Date vaccineDate;
//    private String batchNumber;
//    private String adverseEffects;
//    private String bcgScar; // Present or Absent
//    private Date nextClinicDate;

    // Vaccine type now has a default value to avoid null issues
    @Column(name = "vaccine_type", nullable = false)
    private String vaccineType = "Unknown"; // Set a default value like "Unknown" if not provided

    @Column(name = "vaccine_date", nullable = true)
    private Date vaccineDate;

    @Column(name = "batch_number", nullable = true)
    private String batchNumber;

    @Column(name = "adverse_effects", nullable = true)
    private String adverseEffects;

    @Column(name = "bcg_scar", nullable = true)
    private String bcgScar; // Present or Absent

    @Column(name = "next_clinic_date", nullable = true)
    private Date nextClinicDate;
}
