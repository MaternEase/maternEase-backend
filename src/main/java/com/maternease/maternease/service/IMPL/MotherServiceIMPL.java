package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.AntenatalRiskConditionSlimDTO;
import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.entity.*;
import com.maternease.maternease.exception.MotherNotFoundException;
import com.maternease.maternease.repository.*;
import com.maternease.maternease.service.MotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ClinicScheduleRepo clinicScheduleRepo;


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

    //For fundal height chart
    public List<Map<String, Object>> getFundalHeightData(String motherId) {
        // Query to fetch only the required data
        List<Object[]> data = clinicRecordRepo.findFundalHeightByMotherId(motherId);

        List<Map<String, Object>> chartData = new ArrayList<>();
        for (Object[] record : data) {
            Map<String, Object> point = new HashMap<>();
            point.put("weeks", record[0]); // weeksFromPregnancy
            point.put("fundalHeight", record[1]); // fundalHeight
            chartData.add(point);
        }
        return chartData;
    }

    //For weight gain chart
    public List<Map<String, Object>> getWeightGainChartData(String motherId) {
        List<Object[]> data = clinicRecordRepo.findWeightGainByMotherId(motherId);

        List<Map<String, Object>> chartData = new ArrayList<>();
        for (Object[] record : data) {
            Map<String, Object> point = new HashMap<>();
            point.put("weeks", record[0]); // weeksFromPregnancy
            point.put("weight", record[1]); // newWeight
            chartData.add(point);
        }
        return chartData;
    }

    @Override
    public List<ClinicSchedules> getClinicSchedules(int userId) {
        List<ClinicSchedules> clinicSchedules = new ArrayList<>();
        try {
            Mother mother = motherRepo.getMotherDetails(userId);
            clinicSchedules = clinicScheduleRepo.getClinicSchedule(mother.getClinicId());
        } catch (Exception e){
            System.out.println("Error in getting clinic schedules");
        }
        return clinicSchedules;
    }
}