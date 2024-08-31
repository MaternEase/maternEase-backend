package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MotherDTO {
    private int motherId;
    private String bloodType;
    private int noOfPregnancies;
    private Double height;
    private Double weight;
    private Date dateOfDelivery;
    private String detailsOfHusband;
    private String fullName;
    private int age;
    private List<Long> childIds; // List of Child IDs associated with the expectant mother
}
