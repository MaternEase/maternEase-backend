package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="child")
@Data
public class Child {
    @Id
    private int Id;

    private String guardianName;
    private String gRelationship;
    private String bloodGroup;
    private String status;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "user_role", referencedColumnName = "role"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private OurUsers ourUsers;

    @OneToOne
    @JoinColumn(name = "child_profile_id", referencedColumnName = "id")
    private ChildProfile childProfile;

    @OneToOne
    @JoinColumn(name = "immunization_id", referencedColumnName = "id")
    private Immunization immunization;

    @OneToOne
    @JoinColumn(name = "health_chart_id", referencedColumnName = "id")
    private HealthChart healthChart;

    @OneToOne
    @JoinColumn(name = "child_vision_id", referencedColumnName = "id")
    private ChildVision childVision;

    @OneToOne
    @JoinColumn(name = "child_hearing_id", referencedColumnName = "id")
    private ChildHearing childHearing;

    @OneToOne
    @JoinColumn(name = "child_teeth_id", referencedColumnName = "id")
    private ChildTeeth childTeeth;

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
