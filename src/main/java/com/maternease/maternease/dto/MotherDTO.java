package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MotherDTO {
    private int motherId;
    private int cardId;
    private String clinicId;
    private String contactNo;
    private boolean status;
    private String nic;
    private boolean riskCondition;
    private boolean refdoc;
    private OurUsersDTO ourUsers;
    private AntenatalRiskConditionDTO antenatalRiskCondition;
}
