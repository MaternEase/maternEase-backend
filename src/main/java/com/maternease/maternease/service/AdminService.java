package com.maternease.maternease.service;
import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.MidwifeClinicAssignmentDTO;
import com.maternease.maternease.dto.response.ClinicNameDTO;

import java.util.List;

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
}
