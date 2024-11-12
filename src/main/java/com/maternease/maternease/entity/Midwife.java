package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="midwife")
@Data
public class Midwife {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToMany(mappedBy = "midwife")
//    private List<ClinicCalender> clinicCalenders;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "user_role", referencedColumnName = "role"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private OurUsers ourUsers;
}
