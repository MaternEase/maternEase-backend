package com.maternease.maternease.repository;

import com.maternease.maternease.entity.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildProfileRepo extends JpaRepository<ChildProfile,Integer> {
}
