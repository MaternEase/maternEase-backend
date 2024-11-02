package com.maternease.maternease.dto;

import lombok.Data;

@Data
public class ChildProfileDTO {
    private int id;
    private String babyName;
    private String motherName;
    private int motherAge;
    private String mohArea;
    private String phmArea;
    private String fieldClinicName;
    private String gramaNiladhariDivision;
    private String familyRegistrationNo;
    private String motherRegistrationNo;
    private boolean breastfeedingFirstHour;
    private boolean establishment;
    private boolean connection;
    private boolean testedForArgyroidArthritis;
    private String specialCareReasons;
}
