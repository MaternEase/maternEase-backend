package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OurUsersDTO {
    private OurUsersIdDTO ourUsersId;
    private String fullName;
    private String email;
    private String address;
    private String contactNo;
    private String password;
    private String nic;
    private Date createdAt;
    private String gender;
    private Date dob;
    private int age;
}