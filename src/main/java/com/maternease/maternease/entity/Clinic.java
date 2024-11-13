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

    private String clinicNo;
    private String gramaniDivison;
    private String MOHArea;
    private String PHMArea;
    private String clinicName;

//    @OneToMany(mappedBy = "clinic")
//    private List<Users> users;

//    @ManyToOne
//    @JoinColumn(name = "admin_id")
//    private Admin admin;
//
//    @OneToMany(mappedBy = "clinic")
//    private List<ClinicAttendee> attendees;

    // Many-to-many relationship with Midwife
    @ManyToMany(mappedBy = "assignedClinics")
    private List<Midwife> assignedMidwives;
}
