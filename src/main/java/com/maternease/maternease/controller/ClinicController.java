package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ClinicDto;
import com.maternease.maternease.service.ClinicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/auth/clinics")
public class ClinicController {

    private ClinicService clinicService;

    //    Add Clinic
    @PostMapping
    public ResponseEntity<ClinicDto> createClinic(@RequestBody ClinicDto clinicDto){
        ClinicDto savedClinic = clinicService.createClinic(clinicDto);
        return new ResponseEntity<>(savedClinic, HttpStatus.CREATED);
    }

    //    Get clinic
    @GetMapping("{id}")
    public ResponseEntity<ClinicDto> getClinicById(@PathVariable("id") Long clinicId){
            ClinicDto clinicDto = clinicService.getClinicById(clinicId);
            return ResponseEntity.ok(clinicDto);
    }

    //    Get all clinics
    @GetMapping
    public ResponseEntity<List<ClinicDto>> getAllClinics(){
        List<ClinicDto> clinics = clinicService.getAllClinics();
        return ResponseEntity.ok(clinics);
    }

    //  Update clinic
    @PutMapping("{id}")
    public ResponseEntity<ClinicDto> updateClinic(@PathVariable("id") Long clinicId,
                                                  @RequestBody ClinicDto updatedClinic){
        ClinicDto clinicDto = clinicService.updateClinic(clinicId, updatedClinic);
        return ResponseEntity.ok(clinicDto);
    }

    //    delete clinic
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClinic(@PathVariable("id") Long clinicId){
        clinicService.deleteClinic(clinicId);
        return ResponseEntity.ok("Clinic Deleted Successfully !!!");
    }
}
