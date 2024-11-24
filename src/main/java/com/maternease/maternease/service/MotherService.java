
package com.maternease.maternease.service;

import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.entity.AntenatalRiskCondition;

import java.util.List;
import java.util.Map;

public interface MotherService {
    MProfileDetailsDTO getMotherProfile(String motherId);

    AntenatalRiskCondition getAntenatalRiskCondition(String motherId);

    public List<Map<String, Object>> getFundalHeightData(String motherId);
}
