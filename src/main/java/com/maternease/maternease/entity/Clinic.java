package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "clinic")
@Data
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clinicId;

    private String clinicNo; // Assigned using generateClinicNo method

//    private String gramaniDivison;

    @ElementCollection
    @CollectionTable(name = "gramani_divisions", joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "gramani_division")
    private List<String> gramaniDivisions; // support multiple divisions

    private String MOHArea;

    private String PHMArea;

    private String clinicName;

    // Many-to-many relationship with Midwife
//    @ManyToMany(mappedBy = "assignedClinics")
//    private List<Midwife> assignedMidwives;

    // Relationship with midwives
    @ManyToOne
    @JoinColumn(name = "main_midwife_id")
    private Midwife mainMidwife; // Main midwife for the clinic

    @ManyToMany
    @JoinTable(
            name = "clinic_reserved_midwives",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "midwife_id")
    )
    private List<Midwife> reservedMidwives; // Reserved midwives


    //generate clinic number
    public static String generateClinicNo(String areaCode, int clinicNumber) {
        return areaCode + String.format("%03d", clinicNumber); // Format: AB001
    }
}
