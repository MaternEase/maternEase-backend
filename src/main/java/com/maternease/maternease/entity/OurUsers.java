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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( length = 255)
    private String email;

    @Column(  length = 255)
    private String firstName;

    @Column(  length = 255)
    private String lastName;

    @Column(length = 15)
    private String nic;

    @Column
    private Byte status; // tinyint in database maps to Byte in Java

    @Column(  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column( length = 512)
    private String password;

    @Column( length = 255)
    private String contactNo;

    @Column(  length = 255)
    private String homeNumber;

    @Column( length = 255)
    private String lane;

    @Column( length = 255)
    private String city;

    @Column( length = 255)
    private String postalCode;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column( length = 255)
    private String gender;


    @Column( length = 255)
    private String role;




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
