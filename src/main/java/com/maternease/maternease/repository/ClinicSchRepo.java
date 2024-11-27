package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ClinicSch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicSchRepo extends JpaRepository<ClinicSch, Long> {


}
