package com.maternease.maternease.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//import java.util.Date;

@Data
public class ChildDTO {

    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer age;
    @Getter
    @Setter
    private String guardianName;
    @Getter
    @Setter
    private String contactNumber;
    @Getter
    @Setter
    private String condition;
    @Getter
    @Setter
    private String referToDoctor;
    private String gRelationship;
    private String bloodGroup;
    private String status;
    private String motherId;
    private int birth_order;

    private OurUsersDTO ourUsers;
    private ChildProfileDTO childProfile;
    private ImmunizationDTO immunization;
    private HealthChartDTO healthChart;
    private ChildVisionDTO childVision;
    private ChildHearingDTO childHearing;
    private ChildTeethDTO childTeeth;

    // constructors
    public ChildDTO() {}

    public ChildDTO(int id, String name, Integer age, String guardianName, String contactNumber, String condition, String referToDoctor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.guardianName = guardianName;
        this.contactNumber = contactNumber;
        this.condition = condition;
        this.referToDoctor = referToDoctor;
    }

    // Getters & Setters


}

