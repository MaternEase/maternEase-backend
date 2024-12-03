package com.maternease.maternease.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class MotherRegistrationDTO {

    private String fullName;
    private String email;
    private String address;
    private String contactNo;
    private String nic;
    private String gender;
    private Date dob;
    private String password;//1
    private Date createdAt;//2
    private String motherId;//3
    private String clinicName;
    private Double height;
    private Double weight;
    private Double bmi;
    private Integer poa;
    private Date lastMenstrualDate;
    private String husbandName;
    private Integer husbandAge;
    private String motherEducationLevel;
    private String husbandEducationLevel;
    private String motherOccupation;
    private String husbandOccupation;
    private String registrationNumber;
    private LocalDate registrationDate;
    private String registrationLocation;
    private LocalDate registrationDateEligibleFamilyRegister;
    private String distanceMotherOffice;
    private Integer motherAgeInMarriage;
    private Boolean consanguinity;
    private Boolean rubellaImmunization;
    private Boolean prePregnancyScreeningDone;
    private Boolean preconceptionalFolicAcid;
    private Boolean historyOfSubFertility;
}
