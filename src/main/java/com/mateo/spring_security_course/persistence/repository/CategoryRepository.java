package com.mateo.spring_security_course.persistence.repository;

import com.mateo.spring_security_course.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
