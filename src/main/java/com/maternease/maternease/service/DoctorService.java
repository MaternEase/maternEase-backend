package com.maternease.maternease.service;

import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;

import java.util.List;


public interface DoctorService {
    List<EMotherTableDTO> getAllExpectedMother();

    List<DMotherTableDTO> getAllDeliveredMother();
}
