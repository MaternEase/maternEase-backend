package com.maternease.maternease.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AntenatalRiskConditionDTO {
    private int id;
    private String fullName;
    private Integer age;
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
    private String address;
    private String contactNo;
    private String distanceMotherOffice;
    private Integer motherAgeInMarriage;
    private Boolean consanguinity;
    private Boolean rubellaImmunization;
    private Boolean prePregnancyScreeningDone;
    private Boolean preconceptionalFolicAcid;
    private Boolean historyOfSubFertility;
    private String antenatalRiskConditions;
}
