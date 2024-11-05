package com.maternease.maternease.controller;

import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.dto.response.ResChildProfileDTO;
import com.maternease.maternease.service.MotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mother" )
@CrossOrigin
public class MotherController {

    @Autowired
    private MotherService motherService;

    @GetMapping(path = "/profile/{motherId}")
    public ResponseEntity<MProfileDetailsDTO> getMotherProfile(@PathVariable String motherId){
        MProfileDetailsDTO mProfileDetailsDTO = motherService.getMotherProfile(motherId);
        return ResponseEntity.ok(mProfileDetailsDTO);

    }
}
