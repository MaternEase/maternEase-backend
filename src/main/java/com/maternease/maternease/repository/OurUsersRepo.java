package com.maternease.maternease.repository;

import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.entity.OurUsersId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OurUsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);

    List<OurUsers> findByRoleOrRoleOrRoleOrRoleOrRole(String admin, String mother, String doctor, String midwife, String child);

    List<OurUsers> findByRole(String midwife);

    Optional<OurUsers> findByChildId(String childId);  // Added method for child login


}
