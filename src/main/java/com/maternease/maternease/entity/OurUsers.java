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
@Table(name="ourusers")
@Data
public class OurUsers implements UserDetails {
    @EmbeddedId
    private OurUsersId ourUsersId;

    @Column(length = 255, insertable = false, updatable = false)
    private String role;

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



    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }


    @Override
    public String getUsername() {
        return this.email;
    }

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
