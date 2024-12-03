package com.maternease.maternease.dto.response;

import lombok.Data;

@Data
public class BookingResponseDTO {
    private Long bookingId;
    private Long timeslotId;
    private String timeslotTime;
    private String motherId;
    private String clinicType;
//    private String userName;

    // Getters and Setters
}
