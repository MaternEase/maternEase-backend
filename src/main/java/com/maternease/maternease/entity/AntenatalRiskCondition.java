package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "antenatal_risk_conditions")
@Data
public class AntenatalRiskCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "mother_age")
    private Integer motherAge;

    @Column(name = "husband_name")
    private String husbandName;

    @Column(name = "husband_age")
    private Integer husbandAge;

    @Column(name = "mother_education_level")
    private String motherEducationLevel;

    @Column(name = "husband_education_level")
    private String husbandEducationLevel;

    @Column(name = "mother_occupation")
    private String motherOccupation;

    @Column(name = "husband_occupation")
    private String husbandOccupation;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "registration_location")
    private String registrationLocation;

    @Column(name = "registration_date_eligible_family_register")
    private LocalDate registrationDateEligibleFamilyRegister;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "distance_mother_office")
    private String distanceMotherOffice;

    @Column(name = "mother_age_in_marriage")
    private Integer motherAgeInMarriage;

    @Column(name = "consanguinity")
    private Boolean consanguinity;

    @Column(name = "rubella_immunization")
    private Boolean rubellaImmunization;

    @Column(name = "pre_pregnancy_screening_done")
    private Boolean prePregnancyScreeningDone;

    @Column(name = "preconceptional_folic_acid")
    private Boolean preconceptionalFolicAcid;

    @Column(name = "history_of_sub_fertility")
    private Boolean historyOfSubFertility;

}
