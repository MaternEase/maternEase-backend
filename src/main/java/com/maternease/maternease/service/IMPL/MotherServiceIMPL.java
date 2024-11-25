package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.AntenatalRiskConditionSlimDTO;
import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.entity.*;
import com.maternease.maternease.exception.MotherNotFoundException;
import com.maternease.maternease.repository.ClinicRecordRepo;
import com.maternease.maternease.repository.ClinicRepo;
import com.maternease.maternease.repository.MotherRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.MotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MotherServiceIMPL implements MotherService {

    @Autowired
    private MotherRepo motherRepo;

    @Autowired
    private ClinicRepo clinicRepo;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Autowired
    private ClinicRecordRepo clinicRecordRepo;

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

    @Override
    public AntenatalRiskCondition getAntenatalRiskCondition(String motherId) {
        Mother mother = motherRepo.findById(motherId)
                .orElseThrow(() -> new MotherNotFoundException("Mother not found with id: " + motherId));

        AntenatalRiskCondition antenatalRiskCondition = mother.getAntenatalRiskCondition();
        if (antenatalRiskCondition == null) {
            throw new RuntimeException("No antenatal risk conditions associated with this mother.");
        }
        // Create the DTO and map only the required fields
        AntenatalRiskConditionSlimDTO slimDTO = new AntenatalRiskConditionSlimDTO();
        slimDTO.setConsanguinity(antenatalRiskCondition.getConsanguinity());
        slimDTO.setRubellaImmunization(antenatalRiskCondition.getRubellaImmunization());
        slimDTO.setPrePregnancyScreeningDone(antenatalRiskCondition.getPrePregnancyScreeningDone());
        slimDTO.setPreconceptionalFolicAcid(antenatalRiskCondition.getPreconceptionalFolicAcid());
        slimDTO.setHistoryOfSubFertility(antenatalRiskCondition.getHistoryOfSubFertility());

        return slimDTO;
    }

    public List<Map<String, Object>> getFundalHeightData(String motherId) {
        List<ClinicRecord> records = clinicRecordRepo.findAllByMotherId(motherId);

        // Transform the data to a simplified format
        return records.stream()
                .map(record -> Map.of(
                        "week", (Object) record.getWeeksFromPregnancy(),
                        "fundalHeight", (Object) record.getFundalHeight()
                ))
                .toList();

    }
}