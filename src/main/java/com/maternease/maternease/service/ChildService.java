package com.maternease.maternease.service;

import com.maternease.maternease.dto.HealthChartDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.UpdateChildProfileDTO;
import com.maternease.maternease.dto.response.ResChildProfileDTO;

public interface ChildService {
    ResChildProfileDTO getChildProfile(String childId);

    ResponseDTO updateChildProfile(String childId, UpdateChildProfileDTO updateChildProfileDTO);

    ResponseDTO updateHealthChart(String childId, HealthChartDTO healthChartDTO);

    HealthChartDTO getHealthChartDetails(String childId);
}
