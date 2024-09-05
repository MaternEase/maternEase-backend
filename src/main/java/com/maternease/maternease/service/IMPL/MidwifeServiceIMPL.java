package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
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
}
