package com.maternease.maternease.dto.request;

public class MidwifeClinicAssignmentDTO {

    private int midwifeId;
    private int clinicId;

    // Default constructor
    public MidwifeClinicAssignmentDTO() {
    }

    // Parameterized constructor
    public MidwifeClinicAssignmentDTO(int midwifeId, int clinicId) {
        this.midwifeId = midwifeId;
        this.clinicId = clinicId;
    }

    // Getters and Setters
    public int getMidwifeId() {
        return midwifeId;
    }

    public void setMidwifeId(int midwifeId) {
        this.midwifeId = midwifeId;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public String toString() {
        return "MidwifeClinicAssignmentDTO{" +
                "midwifeId=" + midwifeId +
                ", clinicId=" + clinicId +
                '}';
    }
}
