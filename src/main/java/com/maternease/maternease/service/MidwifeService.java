package com.maternease.maternease.service;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface MidwifeService {
    ResponseDTO registerMother(OurUsersDTO ourUsersDTO);


}
