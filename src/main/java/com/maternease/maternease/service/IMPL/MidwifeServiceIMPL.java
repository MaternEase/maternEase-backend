package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.service.MidwifeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MidwifeServiceIMPL implements MidwifeService {
    @Override
    public String addMother(MotherDTO motherDTO) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setResponseCode("1000");
//        responseDTO.setResponseMzg("Success");
        return "Nishan";
    }
}
