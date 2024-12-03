package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.MidwifeClinicAssignmentDTO;
import com.maternease.maternease.dto.response.ClinicNameDTO;
import com.maternease.maternease.dto.response.ResMBasicDetailsDTO;
import com.maternease.maternease.entity.Midwife;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/midwife-register")
    public ResponseEntity<ReqRes> registerMidwife(@RequestBody ReqRes req){
//        ReqRes response = adminService.registerMidwife(req);
        return ResponseEntity.ok(adminService.registerMidwife(req));
    }

    @PostMapping(path = "/doctor-register")
    public ResponseEntity<ReqRes> registerDoctor(@RequestBody ReqRes req){
        return ResponseEntity.ok(adminService.registerDoctor(req));
    }

    @PostMapping(path = "/clinic-register")
    public ResponseEntity<ResponseDTO> registerClinic(@RequestBody ClinicDTO clinicDTO ){
        ResponseDTO response = adminService.registerClinic(clinicDTO);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/clinics")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        return ResponseEntity.ok(adminService.getAllClinics());
    }

    @GetMapping("/clinic-names")
    public ResponseEntity<List<ClinicNameDTO>> getAllClinicNames() {
        List<ClinicNameDTO> clinicNames = adminService.getAllClinicNames();
        return ResponseEntity.ok(clinicNames);

    }

    @GetMapping("/clinicDetails/{clinicName}")
    public ResponseEntity<ClinicDTO> getClinicDetail(@PathVariable String clinicName) {
        ClinicDTO oneClinicDetails = adminService.getClinicDetail(clinicName);
            return ResponseEntity.ok(oneClinicDetails);

    }

//    @GetMapping(path = "/get-basic-details/{motherId}")
//    public ResponseEntity<ResMBasicDetailsDTO> getBasicDetails(@PathVariable String motherId){
//        ResMBasicDetailsDTO basicDetails = midwifeService.getBasicDetails(motherId);
//        return ResponseEntity.ok(basicDetails);
//    }


    @GetMapping("/midwives")
    public ResponseEntity<List<ReqRes>> getAllMidwives() {
        return ResponseEntity.ok(adminService.getAllMidwives());
    }

    @GetMapping("/assignable-midwives")
    public ResponseEntity<List<ReqRes>> getAssignableMidwives() {
        return ResponseEntity.ok(adminService.getAssignableMidwives());
    }

    @GetMapping("/clinics-needing-midwives")
    public ResponseEntity<List<ClinicDTO>> getClinicsNeedingMidwives() {
        return ResponseEntity.ok(adminService.getClinicsNeedingMidwives());
    }

    @PutMapping("/assign-midwife")
    public ResponseEntity<ResponseDTO> assignMidwifeToClinic(@RequestBody MidwifeClinicAssignmentDTO assignment) {
        ResponseDTO response = adminService.assignMidwifeToClinic(assignment);
        return ResponseEntity.ok(response);
    }


    // Controller to get midwife1 (main midwife) for a clinic
    @GetMapping("/getMidwife1")
    public OurUsers getMidwife1(@RequestParam int clinicId) {
        return adminService.getMainMidwifeForClinic(clinicId);  // Assuming you have logic to get the main midwife (midwife1)
    }

    // Controller to get available midwife2 (supporting midwife) across all clinics
    @GetMapping("/getMidwife2")
    public List<OurUsers> getMidwife2() {
        // Get the list of available midwives for midwife2 (who are not assigned to more than 3 clinics)
        return adminService.getMidwife2And3Dropdown();
    }

    // Controller to get available midwife3 (supporting midwife) across all clinics
    @GetMapping("/getMidwife3")
    public List<OurUsers> getMidwife3() {
        // Get the list of available midwives for midwife3 (who are not assigned to more than 3 clinics)
        return adminService.getMidwife2And3Dropdown();
    }

    @GetMapping("/midwives-not-in-clinic")
    public List<String> getMidwivesNotInClinic() {
        return adminService.getMidwivesNotInClinic();
    }

}
