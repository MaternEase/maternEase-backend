package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.MotherDTO;
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


//    @Override
//    public String addMother(MotherDTO motherDTO) {
//        OurUsers ourUsers = ourUsersRepo.findByEmail(motherDTO.getEmail())
//                .orElse(new OurUsers());
//
//        //        Map data from MotherDTO to OurUsers entity
//
//        ourUsers.setEmail(motherDTO.getEmail());
//        ourUsers.setFullName(motherDTO.getFullName());
//        ourUsers.setNic(motherDTO.getNic());
//        ourUsers.setContactNo(motherDTO.getContactNo());
//
//        //Set and hash the password using the mother's NIC
//
//        String hashedPassword =passwordEncoder.encode(motherDTO.getNic());
//        ourUsers.setPassword(hashedPassword);
//        ourUsers.setRole("MOTHER");
//
//        // Save the OurUsers entity
//
//        OurUsers savedMother = ourUsersRepo.save(ourUsers);
//
//
//        //Map motherDTP to mother entity
//        Mother mother = modelMapper.map(motherDTO,Mother.class);
//        mother.setOurUsers(savedMother);
////
//
//
//
//
//
//
//
//        return "ss";
//    }


}
