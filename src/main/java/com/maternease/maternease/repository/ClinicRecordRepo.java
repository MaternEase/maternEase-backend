package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ClinicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClinicRecordRepo extends JpaRepository<ClinicRecord,Integer> {

    // Find all clinic records for a given motherId
    @Query("SELECT cr FROM ClinicRecord cr WHERE cr.motherId = :motherId")
    List<ClinicRecord> findAllByMotherId(@Param("motherId") String motherId);
}
