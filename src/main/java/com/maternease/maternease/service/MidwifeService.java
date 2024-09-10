package com.maternease.maternease.service;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MidwifeService {
    ResponseDTO registerMother(OurUsersDTO ourUsersDTO);


    List<EMotherTableDTO> getAllExpectedMother();

    List<DMotherTableDTO> getAllDeliveredMother();
}
