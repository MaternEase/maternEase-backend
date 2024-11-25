package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ClinicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ClinicRecordRepo extends JpaRepository<ClinicRecord,Integer> {

    // Find all clinic records for a given motherId
    @Query("SELECT cr FROM ClinicRecord cr WHERE cr.motherId = :motherId")
    List<ClinicRecord> findAllByMotherId(@Param("motherId") String motherId);

    //For weight gain chart
    @Query("SELECT c.weeksFromPregnancy, c.newWeight FROM ClinicRecord c WHERE c.motherId = :motherId ORDER BY c.weeksFromPregnancy ASC")
    List<Object[]> findWeightGainByMotherId(@Param("motherId") String motherId);

    //For fundal height chart
    @Query("SELECT c.weeksFromPregnancy, c.fundalHeight FROM ClinicRecord c WHERE c.motherId = :motherId ORDER BY c.weeksFromPregnancy ASC")
    List<Object[]> findFundalHeightByMotherId(@Param("motherId") String motherId);


}
