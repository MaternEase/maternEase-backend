package com.maternease.maternease.service;
import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.MidwifeClinicAssignmentDTO;
import com.maternease.maternease.dto.response.ClinicNameDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AdminService {

    ReqRes registerMidwife(ReqRes req);

    ReqRes registerDoctor(ReqRes req);

    ResponseDTO registerClinic(ClinicDTO clinicDTO);

    List<ClinicDTO> getAllClinics();
    List<ReqRes> getAllMidwives();
    List<ReqRes> getAssignableMidwives();
    List<ClinicDTO> getClinicsNeedingMidwives();

    ResponseDTO assignMidwifeToClinic(MidwifeClinicAssignmentDTO assignment);

    List<ClinicNameDTO> getAllClinicNames();

    boolean isFirstClinicScheduled();
}
