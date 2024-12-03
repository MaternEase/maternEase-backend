package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Location;
import com.maternease.maternease.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    Optional<Location> findByOurUsers(OurUsers ourUsers);
}
