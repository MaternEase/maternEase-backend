package com.maternease.maternease.repository;

import com.maternease.maternease.entity.AntenatalRiskCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntenatalRiskConditionRepo extends JpaRepository<AntenatalRiskCondition,Integer> {
}
