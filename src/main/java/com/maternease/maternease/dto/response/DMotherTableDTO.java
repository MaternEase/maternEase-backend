package com.maternease.maternease.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class DMotherTableDTO {
    private String motherId;
    private String name; // Full name of the user
    private int age; // Age of the mother
    private Date delivered_date;
    private boolean condition; // Risky or Non-Risky
    private boolean referToDoctor; // Boolean for refer to doctor

}
