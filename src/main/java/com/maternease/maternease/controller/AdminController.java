package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.MidwifeClinicAssignmentDTO;
import com.maternease.maternease.dto.response.ClinicNameDTO;
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

}
