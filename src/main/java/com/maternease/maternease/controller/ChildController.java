package com.maternease.maternease.controller;

import com.maternease.maternease.dto.HealthChartDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.UpdateChildProfileDTO;
import com.maternease.maternease.dto.response.ResChildProfileDTO;
import com.maternease.maternease.entity.HealthChart;
import com.maternease.maternease.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/child")
@CrossOrigin
public class ChildController {

    @Autowired
    private ChildService childService;

    @GetMapping(path = "/profile/{childId}")
    public ResponseEntity<ResChildProfileDTO> getChildProfile(@PathVariable String childId){
        ResChildProfileDTO childProfile = childService.getChildProfile(childId);
        return ResponseEntity.ok(childProfile);

    }

    @PutMapping(path = "/update-profile/{childId}")
    public ResponseEntity<ResponseDTO> updateChildProfile(
            @PathVariable String childId,
            @RequestBody UpdateChildProfileDTO updateChildProfileDTO){
        ResponseDTO updatedDetails = childService.updateChildProfile(childId, updateChildProfileDTO);
        return ResponseEntity.ok(updatedDetails);

    }

    @GetMapping(path = "/health-chart-details/{childId}")
    public ResponseEntity<HealthChartDTO> getHealthChartDetails(@PathVariable String childId){
        HealthChartDTO healthChartDTO = childService.getHealthChartDetails(childId);
        return ResponseEntity.ok(healthChartDTO);
    }

    @PutMapping(path ="/update-health-chart/{childId}")
    public ResponseEntity<ResponseDTO> updateHealthChart(
            @PathVariable String childId,
            @RequestBody HealthChartDTO healthChartDTO){
        ResponseDTO updateDetails = childService.updateHealthChart(childId,healthChartDTO);
        return ResponseEntity.ok(updateDetails);
    }
}
