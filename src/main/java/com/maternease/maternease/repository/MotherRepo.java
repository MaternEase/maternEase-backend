package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Mother;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotherRepo extends JpaRepository<Mother,String> {
    List<Mother> findAllByStatus(int status);

    Mother findTopByOrderByMotherIdDesc();

    Optional <Mother> findByMotherId(String motherId);


}
