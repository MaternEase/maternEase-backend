package com.maternease.maternease.dto;

import lombok.Data;

import java.util.List;

@Data
public class MidwifeDTO {
    private int id;
    private Long clinicId;                     // Reference to the Clinic ID
    private String midId;
    private List<Long> clinicCalenderIds;      // List of ClinicCalender IDs associated with the midwife
}
