package com.maternease.maternease.service.IMPL;



import com.maternease.maternease.dto.request.RecommendationRequest;
import com.maternease.maternease.dto.response.RecommendationResponse;
import com.maternease.maternease.entity.Recommendation;
import com.maternease.maternease.exception.ResourceNotFoundException;
import com.maternease.maternease.repository.RecommendationRepo;
import com.maternease.maternease.service.RecommendationService;
import com.maternease.maternease.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepo repository;

    @Autowired
    private MapperUtil mapper;

    @Override
    public RecommendationResponse saveRecommendation(RecommendationRequest request) {
        Recommendation recommendation = mapper.mapToEntity(request);
        Recommendation savedRecommendation = repository.save(recommendation);
        return mapper.mapToResponse(savedRecommendation);
    }

    @Override
    public RecommendationResponse updateRecommendation(Long id, RecommendationRequest request) {
        Recommendation existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with ID: " + id));
        existing.setText(request.getText());
        Recommendation updated = repository.save(existing);
        return mapper.mapToResponse(updated);
    }

    @Override
    public RecommendationResponse getRecommendation(Long id) {
        Recommendation recommendation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with ID: " + id));
        return mapper.mapToResponse(recommendation);
    }
}
