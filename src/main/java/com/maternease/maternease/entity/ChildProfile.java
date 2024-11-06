package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "child_profile")
@Data
public class ChildProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary key

    @Column(name = "baby_name", nullable = false)
    private String babyName;

    @Column(name = "mother_name", nullable = false)
    private String motherName;
    private boolean breastfeedingFirstHour; // True if yes, false if no
    private boolean establishment; // True if yes, false if no
    private boolean connection; // True if yes, false if no
    private boolean testedForArgyroidArthritis; // True if yes, false if no
    private String specialCareReasons;
}
