package com.maternease.maternease.service;
import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;

public interface AdminService {

    ReqRes registerMidwife(ReqRes req);

    ReqRes registerDoctor(ReqRes req);

    ResponseDTO registerClinic(ClinicDTO clinicDTO);
}
