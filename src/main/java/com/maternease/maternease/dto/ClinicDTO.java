package com.maternease.maternease.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClinicDTO {
    private int clinicId;
    private String gramaniDivison;
    private String MOHArea;
    private String PHMArea;
    private String clinicName;
    private String location;

    private String midwifeOne;
    private String midwifeTwo;
    private String midwifeThree;
//    private List<Long> userIds;         // List of User IDs associated with the clinic
//    private List<Long> attendeeIds;     // List of ClinicAttendee IDs associated with the clinic
}
