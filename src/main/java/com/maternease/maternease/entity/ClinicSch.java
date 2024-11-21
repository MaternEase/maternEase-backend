package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clinic_sch")
@Data
public class ClinicSch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String month; // Example: "January 2024"


    @OneToMany(mappedBy = "clinicSch", cascade = CascadeType.ALL)
    private List<Timeslot> timeslots = new ArrayList<>();


//    @OneToMany
//    @JoinColumn(name = "timetimeslotId", referencedColumnName = "clinicSchId")
//    private List<Timeslot> timeslots = new ArrayList<>();
}
