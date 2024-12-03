package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Midwife;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface MidwifeRepo extends JpaRepository<Midwife,Integer> {
//    @Query("SELECT m FROM Midwife m WHERE COALESCE(m.assignedClinics.size, 0) < 3")
//    List<Midwife> findAssignableMidwives();

    @Query("SELECT m FROM Midwife m WHERE SIZE(m.assignedClinics) < :maxClinicAssignments")
    List<Midwife> findAssignableMidwives(@Param("maxClinicAssignments") int maxClinicAssignments);

    Optional<Midwife> findFirstByOrderByMidIdDesc();


}
