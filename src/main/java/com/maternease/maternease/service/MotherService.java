
package com.maternease.maternease.service;

import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.entity.AntenatalRiskCondition;

public interface MotherService {
    MProfileDetailsDTO getMotherProfile(String motherId);

    AntenatalRiskCondition getAntenatalRiskCondition(String motherId);
}
