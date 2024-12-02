package com.maternease.maternease.service;


import com.maternease.maternease.dto.request.RecommendationRequest;
import com.maternease.maternease.dto.response.RecommendationResponse;

public interface RecommendationService {
    RecommendationResponse saveRecommendation(RecommendationRequest request);
    RecommendationResponse updateRecommendation(Long id, RecommendationRequest request);
    RecommendationResponse getRecommendation(Long id);
}

