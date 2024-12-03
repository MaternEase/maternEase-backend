package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Location")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double latitude;
    private double longitude;
    private String address;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            @JoinColumn(name = "user_role", referencedColumnName = "role")
    })
    private OurUsers ourUsers;
}
