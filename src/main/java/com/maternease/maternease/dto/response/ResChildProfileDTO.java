package com.maternease.maternease.dto.response;

import lombok.Data;

@Data
public class ResChildProfileDTO {
    private String childFullName;
    private String motherFullName;
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
