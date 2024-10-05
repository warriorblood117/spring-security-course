package com.mateo.spring_security_course.controller;

import com.mateo.spring_security_course.dto.SaveCategory;
import com.mateo.spring_security_course.dto.SaveProduct;
import com.mateo.spring_security_course.persistence.entity.Category;
import com.mateo.spring_security_course.persistence.entity.Product;
import com.mateo.spring_security_course.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    ResponseEntity<Page<Category>> findAll(Pageable pageable) {

        Page<Category> categoriesPage = categoryService.findAll(pageable);

        if (categoriesPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoriesPage);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/{categoryId}")
    ResponseEntity<Category> findOneById(@PathVariable Long categoryId) {

        Optional<Category> category = categoryService.findOneById(categoryId);

        if (category.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(category.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    ResponseEntity<Category> createOne(@RequestBody @Valid SaveCategory saveCategory) {

        Category category = this.categoryService.createOne(saveCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @PutMapping("/{categoryId}")
    ResponseEntity<Category> updateOneById(@PathVariable Long categoryId,@RequestBody @Valid SaveCategory saveCategory) {

        Category category = this.categoryService.updateOneById(categoryId,saveCategory);

        return ResponseEntity.status(HttpStatus.OK).body(category);

    }

    @PutMapping("/{categoryId}/disabled")
    ResponseEntity<Category> disableOneById(@PathVariable Long categoryId) {

        Category category = this.categoryService.disableOneById(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(category);

    }

}
