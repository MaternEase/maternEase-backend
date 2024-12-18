package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="mother")
@Data
public class Mother {
    @Id
    private String motherId;
    private int cardId;
    private int clinicId;
    private String contactNo;
    private int status;
    private String nic;
    private boolean riskCondition;
    private boolean refdoc;
    private Date delivered_date;
    private Date expected_date;
    private LocalDate lastMenstrualDate;


    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "user_role", referencedColumnName = "role"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private OurUsers ourUsers;



    // ------------Connect AntenatalRiskCondition------------------
    @OneToOne
    @JoinColumn(name = "ar_condition_id", referencedColumnName = "id")
    private AntenatalRiskCondition antenatalRiskCondition;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;




}