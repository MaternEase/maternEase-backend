package com.maternease.maternease.dto;

import lombok.Data;

@Data
public class HealthChartDTO {
    private int id;
    private double temperature;
    private String stoolColor;
    private String pecaniaState;
    private String skinColor;
    private String eyeCondition;
    private boolean breastfeedingOnly;
    private boolean breastfeedingConnectionOnly;
    private boolean otherComplications;
}
