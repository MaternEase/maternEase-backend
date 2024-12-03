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
    private int id;

//    @Transient
//    private String motherId;
//
//    public String getMotherId() {
//        if (Mother != null) {
//            return Mother.getMotherId();
//        }
//        return null;
//    }

    @Transient
    private String name;

    public String getName() {
        if (childProfile != null) {
            return childProfile.getBabyName();
        }
        return null;
    }



    @Transient
    private Integer age;

    public Integer getAge() {
        if (ourUsers != null) {
            return ourUsers.getAge();
        }
        return null;
    }



    @Column(name = "guardianName")
    private String guardianName;

    @Transient
    private String contactNumber;

    public String getContactNumber() {
        if (ourUsers != null) {
            return ourUsers.getContactNo();
        }
        return null;
    }

    @Column(name = "`condition`")
    private String condition;

    @Column(name = "referToDoctor")
    private String referToDoctor;

    private String gRelationship;
    private String bloodGroup;
    private String status;
    private String motherId;
    private int birth_order;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "user_role", referencedColumnName = "role"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private OurUsers ourUsers;

    @OneToOne
    @JoinColumn(name = "child_profile_id", referencedColumnName = "id")
    @JoinColumn(name = "name", referencedColumnName = "baby_name")
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
