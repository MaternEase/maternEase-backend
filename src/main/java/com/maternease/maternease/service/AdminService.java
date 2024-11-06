package com.maternease.maternease.service;
import com.maternease.maternease.dto.ReqRes;

public interface AdminService {

    ReqRes registerMidwife(ReqRes req);

    ReqRes registerDoctor(ReqRes req);
}
