package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import com.maternease.maternease.entity.Mother;
import com.maternease.maternease.repository.MotherRepo;
import com.maternease.maternease.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class DoctorServiceIMPL implements DoctorService {

    @Autowired
    private MotherRepo motherRepo;


    @Override
    public List<EMotherTableDTO> getAllExpectedMother() {
        List<Mother> expectedMothers = motherRepo.findAllByStatus(0);

        return expectedMothers.stream().map(mother -> {
            EMotherTableDTO eMotherTableDTO = new EMotherTableDTO();

            // Map the relevant fields from the Mother entity to the DTO
            eMotherTableDTO.setMotherId(mother.getMotherId());
            eMotherTableDTO.setName(mother.getOurUsers().getFullName());
            eMotherTableDTO.setAge(mother.getOurUsers().getAge());
            eMotherTableDTO.setCondition(mother.isRiskCondition());
            eMotherTableDTO.setReferToDoctor(mother.isRefdoc()); // Refer to doctor if refdoc is greater than 0
            eMotherTableDTO.setContactNo(mother.getContactNo());  // Assuming you need to fetch this date from somewhere

            return eMotherTableDTO;
        }).collect(Collectors.toList());

    }

    @Override
    public List<DMotherTableDTO> getAllDeliveredMother() {
        List<Mother> deliveredMothers =motherRepo.findAllByStatus(1);
        return deliveredMothers.stream().map(mother -> {
            DMotherTableDTO dMotherTableDTO = new DMotherTableDTO();

//            // Map the relevant fields from the Mother entity to the DTO
            dMotherTableDTO.setMotherId(mother.getMotherId());
            dMotherTableDTO.setName(mother.getOurUsers().getFullName());
            dMotherTableDTO.setAge(mother.getOurUsers().getAge());
            dMotherTableDTO.setCondition(mother.isRiskCondition());
            dMotherTableDTO.setReferToDoctor(mother.isRefdoc()); // Refer to doctor if refdoc is greater than 0
            dMotherTableDTO.setDelivered_date(mother.getDelivered_date());

            return dMotherTableDTO;
        }).collect(Collectors.toList());
    }
}
