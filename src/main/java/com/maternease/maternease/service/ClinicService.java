package com.maternease.maternease.service;

import com.maternease.maternease.dto.ClinicDto;

import java.util.List;

public interface ClinicService {
    ClinicDto createClinic(ClinicDto clinicDto);

    ClinicDto getClinicById(Long clinicId);

    List<ClinicDto> getAllClinics();

    ClinicDto updateClinic(Long clinicId, ClinicDto updatedClinic);

    void deleteClinic(Long clinicId);
}
