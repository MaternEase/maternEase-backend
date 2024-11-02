package com.maternease.maternease.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ourusers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "role"}) // Ensure uniqueness between id and role
})@Data
public class OurUsers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;  // Auto-generated id

    @Column(length = 255, nullable = false)
    private String role;  // No longer part of the primary key

    @Column(  length = 255)
    private String fullName;

    @Column( length = 255)
    private String email;

    @Column( length = 255)
    private String address;

    @Column( length = 255)
    private String contactNo;

    @Column( length = 512)
    private String password;

    @Column(length = 15)
    private String nic;

    @Column(  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    private String gender;

    @Column
    private Date dob;

    @Column
    private int age;

    @Column(length = 255, nullable = true)
    private String childId;

    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "clinicId")
    private Clinic clinic;




    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        if ("CHILD".equalsIgnoreCase(this.role) && this.childId != null) {
            return this.childId;
        } else {
            return this.email;
        }
    }


//    @Override
//    public String getUsername() {
//        return this.email;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
