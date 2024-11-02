package com.maternease.maternease.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class ClinicCalenderDTO {
    private int calenderNo;
    private Date appointmentDate;
    private Time time;
    private Long midwifeId;  // Reference to the Midwife ID
}
