package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChildDTO {
    private int childId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String bloodGroup;
    private String status;
    private Long expectantMotherId;  // Reference to the ExpectantMother ID
    private Long clinicAttendeeId;// Reference to the ClinicAttendee ID
}
