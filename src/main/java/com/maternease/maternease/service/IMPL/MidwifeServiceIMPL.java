package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import com.maternease.maternease.entity.AntenatalRiskCondition;
import com.maternease.maternease.entity.Mother;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.repository.AntenatalRiskConditionRepo;
import com.maternease.maternease.repository.MotherRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.MidwifeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MidwifeServiceIMPL implements MidwifeService {

    @Autowired
    private MotherRepo motherRepo;

    @Autowired
    private AntenatalRiskConditionRepo antenatalRiskConditionRepo;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseDTO registerMother(OurUsersDTO ourUsersDTO) {

        // Map OurUsersDTO to OurUsers entity
        OurUsers newUser = modelMapper.map(ourUsersDTO,OurUsers.class);
        newUser.setRole("MOTHER");
        newUser.setPassword(passwordEncoder.encode(ourUsersDTO.getNic()));  // Set initial password to NIC

        // Save the new user entity in OurUsers
        OurUsers savedUser = ourUsersRepo.save(newUser);

        //Create AntenatalRiskCondition
        AntenatalRiskCondition antenatalRiskCondition = new AntenatalRiskCondition();
        antenatalRiskCondition.setFullName(savedUser.getFullName());
        antenatalRiskCondition.setAge(savedUser.getAge());
        antenatalRiskCondition.setContactNo(savedUser.getContactNo());
        antenatalRiskCondition.setAddress(savedUser.getAddress());

        // Save the AntenatalRiskCondition entry
        AntenatalRiskCondition savedRiskConditions = antenatalRiskConditionRepo.save(antenatalRiskCondition);

        //Create and link Mother entity to OurUsers and AntenatalRiskCondition
        Mother mother = new Mother();
        mother.setNic(savedUser.getNic());
        mother.setContactNo(savedUser.getContactNo());
        mother.setStatus(0); // Expected Mother
        mother.setOurUsers(savedUser);
        mother.setAntenatalRiskCondition(savedRiskConditions);

        //Save the Mother entity
        Mother savedMother = motherRepo.save(mother);

        //Prepare and return response
        ResponseDTO response = new ResponseDTO();
        response.setResponseMzg("Mother registered successfully.");
        return response;
    }

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
