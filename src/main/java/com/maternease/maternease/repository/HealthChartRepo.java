package com.maternease.maternease.repository;

import com.maternease.maternease.entity.HealthChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthChartRepo extends JpaRepository<HealthChart,Integer> {
}
