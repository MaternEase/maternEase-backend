package com.maternease.maternease.dto.response;

import lombok.Data;

@Data
public class EMotherTableDTO {
    private String motherId;
    private String name; // Full name of the user
    private int age; // Age of the mother
    private boolean condition; // Risky or Non-Risky
    private boolean referToDoctor; // Boolean for refer to doctor
    private String contactNo; // Date of delivery
}
