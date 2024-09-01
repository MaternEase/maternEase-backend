package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private OurUsers user;

//    @OneToMany(mappedBy = "admin")
//    private List<Notification> notifications;
}
