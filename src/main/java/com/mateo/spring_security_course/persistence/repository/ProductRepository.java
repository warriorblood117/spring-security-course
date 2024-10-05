package com.mateo.spring_security_course.persistence.repository;

import com.mateo.spring_security_course.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
