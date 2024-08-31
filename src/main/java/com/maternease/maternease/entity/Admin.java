package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    private Users user;

//    @OneToMany(mappedBy = "admin")
//    private List<Notification> notifications;
}
