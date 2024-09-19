package com.maternease.maternease.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ImmunizationDTO {

    private int id; // Primary key
    private String vaccineType;
    private Date vaccineDate;
    private String batchNumber;
    private String adverseEffects;
    private String bcgScar; // Present or Absent
    private Date nextClinicDate;
}
