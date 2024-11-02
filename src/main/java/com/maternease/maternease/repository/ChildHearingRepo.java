package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ChildHearing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildHearingRepo extends JpaRepository<ChildHearing,Integer> {
}
