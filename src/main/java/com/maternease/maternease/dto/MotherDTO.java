package com.maternease.maternease.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MotherDTO {
    private String motherId;
    private int cardId;
    private String clinicId;
    private String contactNo;
    private boolean status;
    private String nic;
    private boolean riskCondition;
    private boolean refdoc;
    private Date delivered_date;
    private OurUsersDTO ourUsers;
    private AntenatalRiskConditionDTO antenatalRiskCondition;
}
