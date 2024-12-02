package com.maternease.maternease.controller;



import com.maternease.maternease.dto.request.RecommendationRequest;
import com.maternease.maternease.dto.response.RecommendationResponse;
import com.maternease.maternease.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    @GetMapping("/{id}")
    public RecommendationResponse getRecommendation(@PathVariable Long id) {
        return service.getRecommendation(id);
    }

    @PostMapping
    public RecommendationResponse saveRecommendation(@RequestBody RecommendationRequest request) {
        return service.saveRecommendation(request);
    }

    @PutMapping("/{id}")
    public RecommendationResponse updateRecommendation(@PathVariable Long id, @RequestBody RecommendationRequest request) {
        return service.updateRecommendation(id, request);
    }
}
