package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepo extends JpaRepository<Recommendation, Long> {
}
