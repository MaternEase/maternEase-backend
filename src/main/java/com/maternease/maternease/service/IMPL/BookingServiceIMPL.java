package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.request.BookingRequestDTO;
import com.maternease.maternease.dto.response.BookingResponseDTO;
import com.maternease.maternease.dto.response.MidwifeBookingDetailsDTO;
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

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private MotherRepo motherRepo;
    @Autowired
    private TimeslotRepo timeslotRepo;

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
        booking.setClinicType(bookingRequest.getClinicType()); // Set clinic type

        bookingRepo.save(booking);

        return mapToResponseDTO(booking, timeslot);
    }

    public String getBookingStatusForTimeslot(Long timeslotId) {
        Timeslot timeslot = timeslotRepo.findById(timeslotId)
                .orElseThrow(() -> new RuntimeException("Timeslot not found"));

        long bookingCount = bookingRepo.countByTimeslot_TimeslotId(timeslot.getTimeslotId());
        long maxBookings = 5; // Maximum number of bookings allowed

        return bookingCount + "/" + maxBookings;
    }

    @Override
    public List<MidwifeBookingDetailsDTO> getAllBookingDetails() {
        List<Timeslot> timeslots = timeslotRepo.findAll(); // Fetch all timeslots
        List<MidwifeBookingDetailsDTO> bookingDetailsList = new ArrayList<>();

        for (Timeslot timeslot : timeslots) {
            List<Booking> bookings = bookingRepo.findByTimeslot_TimeslotId(timeslot.getTimeslotId());

            MidwifeBookingDetailsDTO detailsDTO = new MidwifeBookingDetailsDTO();
            detailsDTO.setTimeslotId(timeslot.getTimeslotId());
            detailsDTO.setTimeslotTime(timeslot.getTime());
            detailsDTO.setBookingCount(bookings.size());

            List<BookingResponseDTO> motherDetails = new ArrayList<>();
            for (Booking booking : bookings) {
                BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
                bookingResponseDTO.setBookingId(booking.getId());
                bookingResponseDTO.setTimeslotId(timeslot.getTimeslotId());
                bookingResponseDTO.setTimeslotTime(timeslot.getTime());
                bookingResponseDTO.setMotherId(booking.getMother().getMotherId());
                bookingResponseDTO.setClinicType(booking.getClinicType()); // Include clinic type

                // Add other required details from Booking entity
                motherDetails.add(bookingResponseDTO);
            }

            detailsDTO.setMothers(motherDetails);
            bookingDetailsList.add(detailsDTO);
        }

        return bookingDetailsList;
    }


    private BookingResponseDTO mapToResponseDTO(Booking booking, Timeslot timeslot) {
        BookingResponseDTO responseDto = new BookingResponseDTO();
        responseDto.setBookingId(booking.getId());
        responseDto.setTimeslotId(timeslot.getTimeslotId());
        responseDto.setTimeslotTime(timeslot.getTime());
        responseDto.setMotherId(booking.getMother().getMotherId());
        responseDto.setClinicType(booking.getClinicType()); // Include clinic type

        return responseDto;
    }
}
