package com.maternease.maternease.controller;

import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import com.maternease.maternease.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping(path = "/api/v1/doctor")
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(path = "/get-all-expected-mother")
    public List<EMotherTableDTO> getAllExpectedMother(){
        List<EMotherTableDTO>  allEMotherTableDTO = doctorService.getAllExpectedMother();
        return allEMotherTableDTO;
    }


    @GetMapping(path = "/get-all-delivered-mother")
    public List<DMotherTableDTO> getAllDeliveredMother(){
        List<DMotherTableDTO> allDMotherTableDTO = doctorService.getAllDeliveredMother();
        return allDMotherTableDTO;
    }
}
