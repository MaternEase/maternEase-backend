package com.maternease.maternease.service;

import com.maternease.maternease.dto.request.BookingRequestDTO;
import com.maternease.maternease.dto.response.BookingResponseDTO;
import com.maternease.maternease.dto.response.MidwifeBookingDetailsDTO;

import java.util.List;

public interface BookingService {


    BookingResponseDTO bookTimeslot(BookingRequestDTO bookingRequest);
    String getBookingStatusForTimeslot(Long timeslotId);

    List<MidwifeBookingDetailsDTO> getAllBookingDetails();

    List<BookingResponseDTO> getBookedTimeslotsByMother(String motherId);


    // Additional methods can be declared as needed
}
