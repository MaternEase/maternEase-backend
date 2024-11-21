package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.request.BookingRequestDTO;
import com.maternease.maternease.dto.response.BookingResponseDTO;
import com.maternease.maternease.entity.Booking;
import com.maternease.maternease.entity.ClinicSch;
import com.maternease.maternease.entity.Mother;
import com.maternease.maternease.entity.Timeslot;
import com.maternease.maternease.repository.BookingRepo;
import com.maternease.maternease.repository.MotherRepo;
import com.maternease.maternease.repository.TimeslotRepo;
import com.maternease.maternease.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceIMPL implements BookingService {

    private final BookingRepo bookingRepo;
    private final MotherRepo motherRepo;
    private final TimeslotRepo timeslotRepo;

    @Autowired
    public BookingServiceIMPL(BookingRepo bookingRepo, MotherRepo motherRepo, TimeslotRepo timeslotRepo) {
        this.bookingRepo = bookingRepo;
        this.motherRepo = motherRepo;
        this.timeslotRepo = timeslotRepo;
    }

    @PostConstruct
    private void initializeDummyData() {
        ClinicSch clinicSch = new ClinicSch();
        clinicSch.setId(1L);
        clinicSch.setMonth("January 2024");

        Timeslot timeslot1 = new Timeslot();
        timeslot1.setTimeslotId(1L);
        timeslot1.setTime("10:00 AM - 11:00 AM");
        timeslot1.setClinicSch(clinicSch);

        Timeslot timeslot2 = new Timeslot();
        timeslot2.setTimeslotId(2L);
        timeslot2.setTime("11:00 AM - 12:00 PM");
        timeslot2.setClinicSch(clinicSch);

        List<Timeslot> timeslots = new ArrayList<>();
        timeslots.add(timeslot1);
        timeslots.add(timeslot2);
        clinicSch.setTimeslots(timeslots);

        timeslotRepo.save(timeslot1);
        timeslotRepo.save(timeslot2);
    }

    @Override
    public BookingResponseDTO bookTimeslot(BookingRequestDTO bookingRequest) {
        Timeslot timeslot = timeslotRepo.findById(bookingRequest.getTimeslotId())
                .orElseThrow(() -> new RuntimeException("Timeslot not found"));

        long bookingCount = bookingRepo.countByTimeslot_TimeslotId(timeslot.getTimeslotId());
        if (bookingCount >= 5) {
            throw new RuntimeException("Timeslot is fully booked");
        }

        Mother mother = motherRepo.findById(bookingRequest.getMotherId())
                .orElseThrow(() -> new RuntimeException("Mother not found"));

        Booking booking = new Booking();
        booking.setMother(mother);
        booking.setTimeslot(timeslot);

        bookingRepo.save(booking);

        return mapToResponseDTO(booking, timeslot);
    }

    private BookingResponseDTO mapToResponseDTO(Booking booking, Timeslot timeslot) {
        BookingResponseDTO responseDto = new BookingResponseDTO();
        responseDto.setBookingId(booking.getId());
        responseDto.setTimeslotId(timeslot.getTimeslotId());
        responseDto.setTimeslotTime(timeslot.getTime());
        responseDto.setMotherId(booking.getMother().getMotherId());
        return responseDto;
    }
}
