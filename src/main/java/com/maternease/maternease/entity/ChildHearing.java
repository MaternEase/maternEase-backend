package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "child_hearing")
@Data
public class ChildHearing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int childs;
}
