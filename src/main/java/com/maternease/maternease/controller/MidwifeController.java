package com.maternease.maternease.controller;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.service.MidwifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class MidwifeController {

    @Autowired
    private MidwifeService midwifeService;

    @PostMapping(path = "/motherSave")
    public String saveMother(@RequestBody MotherDTO motherDTO){
//       return midwifeService.addMother(motherDTO);
        return "Nishan";
    }

    @GetMapping(path = "/sss")
    public String sadee(){
        return "sadeep";
    }
}
