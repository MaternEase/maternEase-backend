package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ClinicSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicScheduleRepo extends JpaRepository<ClinicSchedules, Integer> {
    @Query(value = "SELECT * FROM clinic_schedules WHERE clinic_id = ?1 AND clinic_date > CURRENT_DATE", nativeQuery = true)
    List<ClinicSchedules> getClinicSchedule(int clinicId);
}
