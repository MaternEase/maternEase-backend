package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeslotRepo extends JpaRepository<Timeslot, Long> {
    Optional<Timeslot> findById(Long timeslotId);

}

