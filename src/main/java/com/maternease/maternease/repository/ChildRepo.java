package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepo extends JpaRepository<Child,Integer> {
    int countByMotherId(String motherId);
}
