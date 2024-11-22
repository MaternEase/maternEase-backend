package com.maternease.maternease.service;

import com.maternease.maternease.dto.request.BookingRequestDTO;
import com.maternease.maternease.dto.response.BookingResponseDTO;

public interface BookingService {


    BookingResponseDTO bookTimeslot(BookingRequestDTO bookingRequest);
    String getBookingStatusForTimeslot(Long timeslotId);

    // Additional methods can be declared as needed
}
