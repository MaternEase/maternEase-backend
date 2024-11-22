package com.maternease.maternease.controller;

import com.maternease.maternease.dto.AntenatalRiskConditionDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import com.maternease.maternease.dto.response.MidwifeBookingDetailsDTO;
import com.maternease.maternease.service.BookingService;
import com.maternease.maternease.service.MidwifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDTO> registerMother(@RequestBody OurUsersDTO ourUsersDTO){
        ResponseDTO response = midwifeService.registerMother(ourUsersDTO);
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

    @GetMapping(path ="/get-antenatal-risk-assessment-details/{motherId}")
    public ResponseEntity<AntenatalRiskConditionDTO> getAntenatalRiskAssessmentDetails(@PathVariable String motherId) {
        AntenatalRiskConditionDTO assessmentDetails = midwifeService.getAntenatalRiskAssessmentDetails(motherId);
        return ResponseEntity.ok(assessmentDetails);
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

}
