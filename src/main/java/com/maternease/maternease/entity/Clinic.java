package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "clinic")
@Data
public class Clinic {

    @Id
    private int clinicId;
    private String clinicNo;
    private String gramaniDivison;
    private String MOHArea;
    private String PHMArea;

//    @OneToMany(mappedBy = "clinic")
//    private List<Users> users;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "clinic")
    private List<ClinicAttendee> attendees;
}
