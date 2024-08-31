package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name="midwife")
@Data
public class Midwife {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "midwife")
//    private List<ClinicCalender> clinicCalenders;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;
}
