package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private String postType;
    private String mediaPath;

}
