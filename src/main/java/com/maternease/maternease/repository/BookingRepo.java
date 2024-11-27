package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    long countByTimeslot_TimeslotId(Long timeslotId);
    List<Booking> findByTimeslot_TimeslotId(Long timeslotId);

}

