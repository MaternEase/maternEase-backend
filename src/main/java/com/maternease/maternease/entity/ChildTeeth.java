package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "child_teeth")
@Data
public class ChildTeeth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int childss;

//    // 1st Month
//    private boolean looksAtLight;
//    private boolean looksAtFace;
//
//    // 2nd Month
//    private boolean smilesResponsively;
//    private boolean eyesMoveTogether;
//
//    // 6th Month
//    private boolean looksAroundCautiously;
//    private boolean reachesForObjects;
//    private boolean suspectsFever;
//
//    // 10th Month
//    private boolean thumbIndexPincerGrasp;
//
//    // 12th Month
//    private boolean repeatedPincerGrasp;
}
