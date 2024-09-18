package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="child")
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String childId;

    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String bloodGroup;
    private String status;

//    @ManyToOne
//    @JoinColumn(name = "mother_id")
//    private Mother Mother;

//    @ManyToOne
//    @JoinColumn(name = "clinic_attendee_id")
//    private ClinicAttendee clinicAttendee;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private Users user;
}
