package com.maternease.maternease.service.impl;

import com.maternease.maternease.dto.ClinicDto;
import com.maternease.maternease.entity.Clinic;
import com.maternease.maternease.exception.ResourceNotFoundException;
import com.maternease.maternease.mapper.ClinicMapper;
import com.maternease.maternease.repository.ClinicRepository;
import com.maternease.maternease.service.ClinicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private ClinicRepository clinicRepository;

    @Override
    public ClinicDto createClinic(ClinicDto clinicDto) {

        Clinic clinic = ClinicMapper.mapToClinic(clinicDto);
        Clinic savedClinic = clinicRepository.save(clinic);
        return ClinicMapper.mapToClinicDto(savedClinic);
    }

    @Override
    public ClinicDto getClinicById(Long clinicId) {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Clinic is not exist with the given ID: " + clinicId));
        return ClinicMapper.mapToClinicDto(clinic);
    }

    @Override
    public List<ClinicDto> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream().map((clinic) -> ClinicMapper.mapToClinicDto(clinic))
                .collect(Collectors.toList());
    }

    @Override
    public ClinicDto updateClinic(Long clinicId, ClinicDto updatedClinic) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(
                () -> new ResourceNotFoundException("Clinic is not exist with the given ID: " + clinicId)
        );

        clinic.setName(updatedClinic.getName());
        clinic.setLocation(updatedClinic.getLocation());
        clinic.setCreated_at(updatedClinic.getCreated_at());

        Clinic updatedClinicObj = clinicRepository.save(clinic);

        return ClinicMapper.mapToClinicDto(updatedClinicObj);
    }

    @Override
    public void deleteClinic(Long clinicId) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(
                () -> new ResourceNotFoundException("Clinic is not exist with the given ID: " + clinicId)
        );

        clinicRepository.deleteById(clinicId);
    }
}
