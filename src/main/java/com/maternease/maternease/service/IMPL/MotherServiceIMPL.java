package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.entity.Clinic;
import com.maternease.maternease.entity.Mother;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.exception.ChildNotFoundException;
import com.maternease.maternease.exception.MotherNotFoundException;
import com.maternease.maternease.repository.ClinicRepo;
import com.maternease.maternease.repository.MotherRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.MotherService;
import org.springframework.beans.factory.annotation.Autowired;

public class MotherServiceIMPL implements MotherService {

    @Autowired
    private MotherRepo motherRepo;

    @Autowired
    private ClinicRepo clinicRepo;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Override
    public MProfileDetailsDTO getMotherProfile(String motherId) {
        Mother mother = motherRepo.findById(motherId)
                .orElseThrow(() -> new MotherNotFoundException("Mother not found with id: " + motherId));

        Integer userId = mother.getOurUsers().getId();

        OurUsers ourUsers = ourUsersRepo.findById(userId)
                .orElseThrow(() -> new MotherNotFoundException("User not found with id :" + userId));

        MProfileDetailsDTO mProfileDetailsDTO = new MProfileDetailsDTO();

        mProfileDetailsDTO.setMotherId(mother.getMotherId());
        mProfileDetailsDTO.setFullName(ourUsers.getFullName());
        mProfileDetailsDTO.setEmail(ourUsers.getEmail());
        mProfileDetailsDTO.setContactNo(ourUsers.getContactNo());


        Clinic clinic = clinicRepo.findByClinicName(ourUsers.getClinicName());
        if (clinic != null) {

            mProfileDetailsDTO.setMOHArea(clinic.getMOHArea());
            mProfileDetailsDTO.setPHMArea(clinic.getPHMArea());
            mProfileDetailsDTO.setClinicNo(clinic.getClinicNo());

        }
        return mProfileDetailsDTO;
    }
}
