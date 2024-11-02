package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "health_chart")
@Data
public class HealthChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double temperature;
    private String stoolColor;

    @Column(name = "pecania_state")
    private String pecaniaState;

    @Column(name = "skin_color")
    private String skinColor;

    @Column(name = "eye_condition")
    private String eyeCondition;

    @Column(name = "breastfeeding_only")
    private boolean breastfeedingOnly;

    @Column(name = "breastfeeding_connection_only")
    private boolean breastfeedingConnectionOnly;

    @Column(name = "other_complications")
    private boolean otherComplications;
}
