//package com.maternease.maternease.controller;
//
//import com.maternease.maternease.dto.response.ResChildProfileDTO;
//import com.maternease.maternease.service.ChildService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/child")
//@CrossOrigin
//public class ChildController {
//
//    @Autowired
//    private ChildService childService;
//
//    @GetMapping(path = "/profile/{childId}")
//    public ResponseEntity<ResChildProfileDTO> getChildProfile(@PathVariable String childId){
//        ResChildProfileDTO childProfile = childService.getChildProfile(childId);
//        return ResponseEntity.ok(childProfile);
//
//    }
//}
