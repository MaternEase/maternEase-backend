package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog,Integer > {
}
