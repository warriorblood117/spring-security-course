package com.mateo.spring_security_course.controller;

import com.mateo.spring_security_course.persistence.entity.Product;
import com.mateo.spring_security_course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    ResponseEntity<Page<Product>> findAll(Pageable pageable){

        Page<Product> productsPage = productService.findAll(pageable);

        if(productsPage.hasContent()){
            return ResponseEntity.status(HttpStatus.OK).body(productsPage);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/{productId}")
    ResponseEntity<Product> findOneById(@PathVariable Long productId){

        Optional<Product> product = productService.findOneById(productId);

        if(product.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
