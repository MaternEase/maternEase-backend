package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepo extends JpaRepository<Child,String> {

    int countByMotherId(String motherId);

    Optional <Child> findByOurUsers_Id(Integer userId);


}
