package com.maternease.maternease.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResClinicRecordDTO {

    private Integer weeksFromPregnancy;
    private Float fundalHeight;
    private Float newWeight;
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
