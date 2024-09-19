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


    @Column(name = "vaccine_type", nullable = false)
    private String vaccineType;
    private Date vaccineDate;
    private String batchNumber;
    private String adverseEffects;
    private String bcgScar; // Present or Absent
    private Date nextClinicDate;

}
