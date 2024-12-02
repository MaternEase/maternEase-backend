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

//    private String gramaniDivison;

    @ElementCollection
    @CollectionTable(name = "gramani_divisions", joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "gramani_division")
    private List<String> gramaniDivisions; // support multiple divisions

    private String MOHArea;

    private String PHMArea;

    private String clinicName;
    private String location;

    private String midwifeOne;
    private String midwifeTwo;
    private String midwifeThree;
    private int firstClinicWeek;
    private int secondClinicWeek;
    private int clinicDay;


    @ManyToMany
    @JoinTable(
            name = "clinic_reserved_midwives",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "midwife_id")
    )
    private List<Midwife> reservedMidwives; // Reserved midwives
}
