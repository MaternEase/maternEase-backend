package com.maternease.maternease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClinicDto {
    private Long id;
    private String name;
    private String location;
    private String created_at;
}
