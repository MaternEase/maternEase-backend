package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Immunization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmunizationRepo extends JpaRepository<Immunization,Integer> {
}
