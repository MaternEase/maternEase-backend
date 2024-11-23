package com.maternease.maternease.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClinicRecordUpdateDTO {

    private Integer weeksFromPregnancy;
    private Float fundalHeight;
    private Integer fetalHeartRate;
    private Boolean anemia;
    private Boolean fetalMovement;
    private Boolean sugarInUrine;
    private Boolean albuminInUrine;
    private Boolean ironFolate;
    private Boolean vitaminC;
    private Boolean calcium;
    private Boolean thriposha;
    private Boolean tetanusVaccine;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String motherId; // Reference to Mother's ID

}
