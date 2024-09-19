package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ChildVision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildVisionRepo extends JpaRepository<ChildVision,Integer> {
}
