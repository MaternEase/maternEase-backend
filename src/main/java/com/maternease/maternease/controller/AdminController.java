package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
