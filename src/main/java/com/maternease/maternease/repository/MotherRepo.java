package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Mother;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherRepo extends JpaRepository<Mother,Integer> {
}
