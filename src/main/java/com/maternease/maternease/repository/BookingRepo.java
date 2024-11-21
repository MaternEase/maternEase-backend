package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    long countByTimeslot_TimeslotId(Long timeslotId);
}

