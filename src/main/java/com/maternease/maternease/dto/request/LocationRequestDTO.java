package com.maternease.maternease.dto.request;

import lombok.Data;

@Data
public class LocationRequestDTO {
    private int id;
    private double latitude;
    private double longitude;
    private String address;
    private int userId;
    private String role;
}
