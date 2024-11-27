package com.maternease.maternease.controller;

import com.maternease.maternease.dto.AntenatalRiskConditionDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.ClinicRecordUpdateDTO;
import com.maternease.maternease.dto.request.MotherRegistrationDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;

import com.maternease.maternease.dto.response.MidwifeBookingDetailsDTO;
import com.maternease.maternease.service.BookingService;

import com.maternease.maternease.dto.response.ResClinicRecordDTO;
import com.maternease.maternease.dto.response.ResMBasicDetailsDTO;

import com.maternease.maternease.service.MidwifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/midwife")
@CrossOrigin
public class MidwifeController {

    @Autowired
    private MidwifeService midwifeService;
    @Autowired
    private BookingService bookingService;

    @GetMapping(path = "/sss")
    public String sadee(){
        return "sadeep";
    }

    @PostMapping(path = "/mother-register")
    public ResponseEntity<ResponseDTO> registerMother(@RequestBody MotherRegistrationDTO motherRegistrationDTO){
        ResponseDTO response = midwifeService.registerMother(motherRegistrationDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/get-all-expected-mother")
    public List<EMotherTableDTO> getAllExpectedMother(){
        List<EMotherTableDTO>  allEMotherTableDTO = midwifeService.getAllExpectedMother();
        return allEMotherTableDTO;
    }

    @GetMapping(path = "/get-all-delivered-mother")
    public List<DMotherTableDTO> getAllDeliveredMother(){
        List<DMotherTableDTO> allDMotherTableDTO = midwifeService.getAllDeliveredMother();
        return allDMotherTableDTO;
    }

    @PostMapping(path = "/child-register")
    public ResponseEntity<ResponseDTO> registerChild(@RequestBody OurUsersDTO ourUsersDTO ){
        ResponseDTO response = midwifeService.registerChild(ourUsersDTO);
        return ResponseEntity.ok(response);
    }

//    @GetMapping(path ="/get-antenatal-risk-assessment-details/{motherId}")
//    public ResponseEntity<AntenatalRiskConditionDTO> getAntenatalRiskAssessmentDetails(@PathVariable String motherId) {
//        AntenatalRiskConditionDTO assessmentDetails = midwifeService.getAntenatalRiskAssessmentDetails(motherId);
//        return ResponseEntity.ok(assessmentDetails);
//    }


    @GetMapping(path = "/get-basic-details/{motherId}")
    public ResponseEntity<ResMBasicDetailsDTO> getBasicDetails(@PathVariable String motherId){
        ResMBasicDetailsDTO basicDetails = midwifeService.getBasicDetails(motherId);
        return ResponseEntity.ok(basicDetails);
    }

//    others data can be get use this /get-antenatal-risk-assessment-details/{motherId} like wise

//    ...................................................................

    @PutMapping(path = "/update-antenatal-risk-assessment-details/{motherId}")
    public ResponseEntity<ResponseDTO> updateAntenatalRiskAssessmentDetails(
            @PathVariable String motherId,
            @RequestBody AntenatalRiskConditionDTO antenatalRiskConditionDTO) {
        ResponseDTO updatedDetails = midwifeService.updateAntenatalRiskAssessmentDetails(motherId, antenatalRiskConditionDTO);
        return ResponseEntity.ok(updatedDetails);
    }



    @GetMapping("/bookings")
    public ResponseEntity<List<MidwifeBookingDetailsDTO>> getAllBookingDetails() {
        List<MidwifeBookingDetailsDTO> bookingDetails = bookingService.getAllBookingDetails();
        return ResponseEntity.ok(bookingDetails);
    }

//     @PostMapping(path = "/clinic-record")
//     public ResponseEntity<ResponseDTO> addClinicRecord(@RequestBody ClinicRecordUpdateDTO clinicRecordUpdateDTO){
//         ResponseDTO response = midwifeService.addClinicRecord(clinicRecordUpdateDTO);

    @PostMapping(path = "/clinic-record/{motherId}")
    public ResponseEntity<ResponseDTO> addClinicRecord(@PathVariable String motherId, @RequestBody ClinicRecordUpdateDTO clinicRecordUpdateDTO){
        ResponseDTO response = midwifeService.addClinicRecord(motherId ,clinicRecordUpdateDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/clinic-record/{motherId}")
    public ResponseEntity<List<ResClinicRecordDTO>> getClinicRecord(@PathVariable String motherId){
        List<ResClinicRecordDTO> recordDetails =(midwifeService.getClinicRecord(motherId));
        return ResponseEntity.ok(recordDetails);

    }

}
