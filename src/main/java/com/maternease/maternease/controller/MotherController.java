package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.BookingRequestDTO;
import com.maternease.maternease.dto.request.LocationRequestDTO;
import com.maternease.maternease.dto.response.BookingResponseDTO;
import com.maternease.maternease.dto.response.MProfileDetailsDTO;
import com.maternease.maternease.entity.AntenatalRiskCondition;
import com.maternease.maternease.entity.ClinicSchedules;
import com.maternease.maternease.service.BookingService;
import com.maternease.maternease.service.LocationService;
import com.maternease.maternease.service.MotherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mother")
@CrossOrigin
public class MotherController {

    @Autowired
    private MotherService motherService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private LocationService locationService;


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

    @GetMapping("/timeslots/{id}/status")
    public ResponseEntity<String> getTimeslotBookingStatus(@PathVariable Long id) {
        String status = bookingService.getBookingStatusForTimeslot(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping(path = "/antenatal-risk/{motherId}")
    public ResponseEntity<AntenatalRiskCondition> getAntenatalRiskCondition(@PathVariable String motherId) {
        AntenatalRiskCondition antenatalRiskCondition = motherService.getAntenatalRiskCondition(motherId);
        return ResponseEntity.ok(antenatalRiskCondition);
    }

    @GetMapping("/{motherId}/fundal-height")
    public ResponseEntity<List<Map<String, Object>>> getFundalHeightData(@PathVariable String motherId) {
        List<Map<String, Object>> data = motherService.getFundalHeightData(motherId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/weight-gain/{motherId}")
    public ResponseEntity<List<Map<String, Object>>> getWeightGainData(@PathVariable String motherId) {
        List<Map<String, Object>> data = motherService.getWeightGainChartData(motherId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/get-schedules/{userId}")
    public ResponseEntity<List<ClinicSchedules>> getClinicSchedules(@PathVariable int userId){
        List<ClinicSchedules> clinicSchedules = motherService.getClinicSchedules(userId);
        return ResponseEntity.ok(clinicSchedules);
    }

    @PostMapping("/set-location")
    public ResponseEntity<ResponseDTO> saveLocation(@RequestBody @Valid LocationRequestDTO locationRequest) {
        ResponseDTO response = locationService.saveLocation(locationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-location/{userId}")
    public ResponseEntity<LocationRequestDTO> getLocationByUserId(@PathVariable("userId") int userId) {
        LocationRequestDTO locationDTO = locationService.getLocationByUserId(userId);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }

}
