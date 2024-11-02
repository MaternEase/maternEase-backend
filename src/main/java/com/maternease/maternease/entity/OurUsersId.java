package com.maternease.maternease.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class OurUsersId implements Serializable {
    private String role;
    private int id;

    // Default constructor
    public OurUsersId() {}

    // Parameterized constructor
    public OurUsersId(String role, int id) {
        this.role = role;
        this.id = id;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OurUsersId that = (OurUsersId) o;
        return id == that.id && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, id);
    }
}
