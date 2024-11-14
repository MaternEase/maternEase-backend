package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="clinic_calender")
@Data
public class ClinicCalender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calenderNo;

    private Date appointmentDate;
    private Time time;
}
