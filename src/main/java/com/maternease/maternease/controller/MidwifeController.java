package com.maternease.maternease.controller;

import com.maternease.maternease.dto.AntenatalRiskConditionDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.ClinicRecordUpdateDTO;
import com.maternease.maternease.dto.request.MotherRegistrationDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;

import com.maternease.maternease.dto.response.MidwifeBookingDetailsDTO;
import com.maternease.maternease.entity.Blog;
import com.maternease.maternease.service.BookingService;

import com.maternease.maternease.dto.response.ResClinicRecordDTO;
import com.maternease.maternease.dto.response.ResMBasicDetailsDTO;

import com.maternease.maternease.service.MidwifeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

//import static com.maternease.maternease.service.IMPL.MidwifeServiceIMPL.UPLOAD_DIR;

@RestController
@RequestMapping("/api/v1/midwife")
@CrossOrigin
public class MidwifeController {

    @Autowired
    private MidwifeService midwifeService;
    @Autowired
    private BookingService bookingService;

    private static final String UPLOAD_DIR = "uploads";


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

    @PostMapping(path = "/blog-create")
    public ResponseEntity<ResponseDTO> createBlogPost(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String postType,
            @RequestParam(required = false) MultipartFile media) {

        try {
            ResponseDTO response = midwifeService.createBlogPost(title, content, postType, media);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseDTO());
        }
    }

    // Endpoint to get all blog posts
    @GetMapping(path = "/blogs-get-all")
    public ResponseEntity<List<Blog>> getAllBlogPosts() {
        List<Blog> blogPosts = midwifeService.getAllBlogPosts();
        return ResponseEntity.ok(blogPosts);
    }

    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        // Specify the directory where files are stored
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);

        // Create a Resource object from the file
        Resource resource = (Resource) new FileSystemResource(filePath.toFile());

        // Check if the file exists
        if (!((FileSystemResource) resource).exists()) {
            return ResponseEntity.notFound().build(); // Return 404 if file is not found
        }

        // Return the file with content disposition header (to handle different file types)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + ((FileSystemResource) resource).getFilename() + "\"")
                .body(resource);
    }


}
