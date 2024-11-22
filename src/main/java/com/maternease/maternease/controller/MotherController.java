package com.maternease.maternease.controller;

import com.maternease.maternease.dto.request.BookingRequestDTO;
import com.maternease.maternease.dto.response.BookingResponseDTO;
import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.service.BookingService;
import com.maternease.maternease.service.MotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mother")
@CrossOrigin
public class MotherController {

    @Autowired
    private MotherService motherService;

    @Autowired
    private BookingService bookingService;

    @GetMapping(path = "/profile/{motherId}")
    public ResponseEntity<MProfileDetailsDTO> getMotherProfile(@PathVariable String motherId) {
        MProfileDetailsDTO mProfileDetailsDTO = motherService.getMotherProfile(motherId);
        return ResponseEntity.ok(mProfileDetailsDTO);
    }

    @PostMapping(path = "/booking")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequest) {
        BookingResponseDTO response = bookingService.bookTimeslot(bookingRequest);
        return ResponseEntity.ok(response);
    }



}
