package com.maternease.maternease.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MidwifeBookingDetailsDTO {
    private Long timeslotId;
    private String timeslotTime;
    private int bookingCount;
    private List<BookingResponseDTO> mothers; // Reuse the existing DTO


    // Getters and setters
}
