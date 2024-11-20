package com.maternease.maternease.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResChildProfileDTO {
    private String childFullName;
    private String motherFullName;
    private int motherAge;
    private String mohArea;
    private String phmArea;
    private String fieldClinicName;
//    private String gramaNiladhariDivision;
    private List<String> gramaNiladhariDivision;
    private String familyRegistrationNo;
    private String motherRegistrationNo;
    private Date motherRegitrationDate;
    private boolean breastfeedingFirstHour;
    private boolean establishment;
    private boolean connection;
    private boolean testedForArgyroidArthritis;
    private String specialCareReasons;
    private String address;


}
