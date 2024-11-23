package com.maternease.maternease.dto.request;

import lombok.Data;

@Data
public class BookingRequestDTO {

        private String motherId;
        private Long timeslotId;
        private String clinicType;

        // Getters and Setters

}
