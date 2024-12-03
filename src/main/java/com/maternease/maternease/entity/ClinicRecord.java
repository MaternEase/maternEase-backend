package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="clinic_records")
@Data
public class ClinicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(name = "weeks_from_pregnancy", nullable = false)
    private Integer weeksFromPregnancy;

    @Column(name = "fundal_height")
    private Float fundalHeight;

    @Column(name = "new_weight")
    private Float newWeight;

    @Column(name = "fetal_heart_rate")
    private Integer fetalHeartRate;

    @Column(name = "anemia")
    private Boolean anemia;

    @Column(name = "fetal_movement")
    private Boolean fetalMovement;

    @Column(name = "sugar_in_urine")
    private Boolean sugarInUrine;

    @Column(name = "albumin_in_urine")
    private Boolean albuminInUrine;

    @Column(name = "iron_folate")
    private Boolean ironFolate;

    @Column(name = "vitamin_c")
    private Boolean vitaminC;

    @Column(name = "calcium")
    private Boolean calcium;

    @Column(name = "thriposha")
    private Boolean thriposha;

    @Column(name = "tetanus_vaccine")
    private Boolean tetanusVaccine;

//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime createdAt;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "mother_id")
    private String motherId;


}
