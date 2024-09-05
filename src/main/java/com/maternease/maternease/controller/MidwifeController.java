package com.maternease.maternease.controller;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.service.MidwifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/midwife")
@CrossOrigin
public class MidwifeController {

    @Autowired
    private MidwifeService midwifeService;

    @GetMapping(path = "/sss")
    public String sadee(){
        return "sadeep";
    }

    @PostMapping(path = "/mother-register")
    public ResponseEntity<ResponseDTO> registerMother(@RequestBody OurUsersDTO ourUsersDTO){
        ResponseDTO response = midwifeService.registerMother(ourUsersDTO);
        return ResponseEntity.ok(response);
    }
}
