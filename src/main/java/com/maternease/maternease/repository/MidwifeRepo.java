package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Midwife;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidwifeRepo extends JpaRepository<Midwife,Integer> {
}
