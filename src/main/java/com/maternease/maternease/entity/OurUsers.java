package com.maternease.maternease.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="OurUsers")
@Data
public class OurUsers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column( nullable = false, length = 255)
    private String firstName;

    @Column( nullable = false, length = 255)
    private String lastName;

    @Column(length = 15, nullable = false)
    private String nic;

    @Column(nullable = false)
    private Byte status; // tinyint in database maps to Byte in Java

    @Column( nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = false, length = 512)
    private String password;

    @Column( nullable = true, length = 255)
    private String contactNo;

    @Column( nullable = false, length = 255)
    private String homeNumber;

    @Column(nullable = false, length = 255)
    private String lane;

    @Column(nullable = false, length = 255)
    private String city;

    @Column(nullable = false, length = 255)
    private String postalCode;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(nullable = true, length = 255)
    private String gender;


    @Column(nullable = false, length = 255)
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }



    @Override
    public String getUsername() {
        return "email";
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
