package com.maternease.maternease.mapper;

import com.maternease.maternease.dto.ClinicDto;
import com.maternease.maternease.entity.Clinic;

public class ClinicMapper {

    public static ClinicDto mapToClinicDto(Clinic clinic){
        return new ClinicDto(
                clinic.getId(),
                clinic.getName(),
                clinic.getLocation(),
                clinic.getCreated_at()
        );
    }

    public static Clinic mapToClinic(ClinicDto clinicDto){
        return new Clinic(
                clinicDto.getId(),
                clinicDto.getName(),
                clinicDto.getLocation(),
                clinicDto.getCreated_at()
        );
    }
}
