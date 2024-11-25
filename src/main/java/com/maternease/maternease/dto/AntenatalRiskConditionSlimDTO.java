package com.maternease.maternease.dto;

import com.maternease.maternease.entity.AntenatalRiskCondition;
import lombok.Data;

@Data
public class AntenatalRiskConditionSlimDTO extends AntenatalRiskCondition {
    private Boolean consanguinity;
    private Boolean rubellaImmunization;
    private Boolean prePregnancyScreeningDone;
    private Boolean preconceptionalFolicAcid;
    private Boolean historyOfSubFertility;

    // Getters and Setters
}
