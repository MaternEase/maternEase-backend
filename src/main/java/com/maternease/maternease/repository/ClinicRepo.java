package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepo extends JpaRepository<Clinic,Integer> {

    Clinic findByClinicName(String clinicName);

}
