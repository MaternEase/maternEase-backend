package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="doctor")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private OurUsers user;

//    @OneToMany(mappedBy = "doctor")
//    private List<Notification> notifications;
}
