package com.mateo.spring_security_course.service;

import com.mateo.spring_security_course.dto.SaveCategory;
import com.mateo.spring_security_course.persistence.entity.Category;
import com.mateo.spring_security_course.persistence.entity.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(@Valid SaveCategory saveCategory);

    Category updateOneById(Long categoryId, @Valid SaveCategory saveCategory);

    Category disableOneById(Long categoryId);
}
