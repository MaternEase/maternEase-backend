package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "child_vision")
@Data
public class ChildVision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int childsss;

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
//    private boolean triesToTouch;
//    private boolean suspectsFever;
//
//    // 10th Month
//    private boolean capturesSmallMaterials10Month;
//
//    // 12th Month
//    private boolean capturesSmallMaterials12Month;
}
