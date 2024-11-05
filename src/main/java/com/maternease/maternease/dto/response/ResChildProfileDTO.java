package com.maternease.maternease.dto.response;

import lombok.Data;

import java.util.Date;

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
    private Date motherRegitrationDate;
    private boolean breastfeedingFirstHour;
    private boolean establishment;
    private boolean connection;
    private boolean testedForArgyroidArthritis;
    private String specialCareReasons;
    private String address;


    @Data
    public static class MProfileDetailsDTO {
        private String motherId;
        private String fullName;
        private String email;
        private String MOHArea;
        private String PHMArea;
        private String clinicNo;
        private String contactNo;
    }
}
