package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ClinicAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicAttendeeRepo extends JpaRepository<ClinicAttendee,Integer> {
}
