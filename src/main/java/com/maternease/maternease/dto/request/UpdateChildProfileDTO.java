package com.maternease.maternease.dto.request;

import lombok.Data;

@Data
public class UpdateChildProfileDTO {
    private String babyName;
    private String familyRegistrationNo;
    private boolean breastfeedingFirstHour;
    private boolean establishment;
    private boolean connection;
    private boolean testedForArgyroidArthritis;
    private String specialCareReasons;
}
