package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "clinic_schedules")
public class ClinicSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    private String clinicType;

    @Temporal(TemporalType.DATE)
    private Date clinicDate;

    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "clinicId", nullable = false)
    private Clinic clinic;
}
