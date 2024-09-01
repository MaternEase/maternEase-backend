package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="mother")
@Data
public class Mother {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int motherId;
//    private String bloodType;
//    private int noOfPregnancies;
//    private Double height;
//    private Double weight;
//    private Date dateOfDelivery;
//    private String detailsOfHusband;
    private String motherName;
    private String address;
    private Date DOB;
    private int motherAge;
    private String telephoneNumber;
    private int status;
    private String motherNIC;
    private String motherEmail;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private OurUsers user;



// ------------Connect AntenatalRiskCondition------------------
@OneToOne
@JoinColumn(name = "ar_condition_id", referencedColumnName = "id")
private AntenatalRiskCondition antenatalRiskCondition;



}
