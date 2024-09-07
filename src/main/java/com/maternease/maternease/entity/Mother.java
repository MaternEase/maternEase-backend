package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="mother")
@Data
public class Mother {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int motherId;
    private int cardId;
    private String clinicId;
    private String contactNo;
    private int status;
    private String nic;
    private boolean riskCondition;
    private boolean refdoc;

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



}
