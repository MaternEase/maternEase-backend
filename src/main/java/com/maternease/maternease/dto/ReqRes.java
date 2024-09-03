package com.maternease.maternease.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.maternease.maternease.entity.OurUsers;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String massage;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String fullName;
//    private String lastName;
    private String nic;
    private Byte status;
    private Date createdAt;
    private String password;
    private String contactNo;
    private String homeNumber;
    private String lane;
    private String city;
    private String postalCode;
    private Date dob;
    private String gender;
    private String role;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;


}
