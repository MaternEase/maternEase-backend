package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChildDTO {

    private int id;
    private String guardianName;
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
}
