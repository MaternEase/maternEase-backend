package com.maternease.maternease.service;

import com.maternease.maternease.dto.AntenatalRiskConditionDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.ClinicRecordUpdateDTO;
import com.maternease.maternease.dto.request.MotherRegistrationDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import com.maternease.maternease.dto.response.ResClinicRecordDTO;
import com.maternease.maternease.dto.response.ResMBasicDetailsDTO;

import java.util.List;

public interface MidwifeService {
    ResponseDTO registerMother(MotherRegistrationDTO motherRegistrationDTO);


    List<EMotherTableDTO> getAllExpectedMother();

    List<DMotherTableDTO> getAllDeliveredMother();

    ResponseDTO registerChild(OurUsersDTO ourUsersDTO);

//    AntenatalRiskConditionDTO getAntenatalRiskAssessmentDetails(String motherId);

    ResponseDTO updateAntenatalRiskAssessmentDetails(String motherId, AntenatalRiskConditionDTO antenatalRiskConditionDTO);

    ResMBasicDetailsDTO getBasicDetails(String motherId);

    ResponseDTO addClinicRecord(String motherId,ClinicRecordUpdateDTO clinicRecordUpdateDTO);

    List<ResClinicRecordDTO> getClinicRecord(String motherId);
}
