package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name="midwife")
@Data
public class Midwife {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Define many-to-many relationship with Clinic
    @ManyToMany
    @JoinTable(
            name = "midwife_clinic",
            joinColumns = @JoinColumn(name = "midwife_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    @JsonIgnore  // To prevent infinite recursion during JSON serialization
    private List<Clinic> assignedClinics;

    // Link with OurUsers to inherit user details
    // One-to-one relationship with OurUsers for user details
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "user_role", referencedColumnName = "role"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })

    private OurUsers ourUsers;

    //    @OneToMany(mappedBy = "midwife")
//    private List<ClinicCalender> clinicCalenders;

//    @ManyToOne
//    @JoinColumn(name = "clinic_id")
//    private Clinic clinic;

//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name = "user_role", referencedColumnName = "role"),
//            @JoinColumn(name = "user_id", referencedColumnName = "id")
//    })
}
