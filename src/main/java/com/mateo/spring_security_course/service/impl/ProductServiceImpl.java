package com.mateo.spring_security_course.service.impl;

import com.mateo.spring_security_course.dto.SaveProduct;
import com.mateo.spring_security_course.exception.ObjectNotFoundException;
import com.mateo.spring_security_course.persistence.entity.Category;
import com.mateo.spring_security_course.persistence.entity.Product;
import com.mateo.spring_security_course.persistence.repository.CategoryRepository;
import com.mateo.spring_security_course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOneById(Long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public Product createOne(SaveProduct saveProduct) {
        Product product = new Product();
        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());
        product.setStatus(Product.ProductStatus.ENABLE);

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateOneById(Long productId, SaveProduct saveProduct) {
        Product productFromDB = this.productRepository.findById(productId).orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));
        productFromDB.setName(saveProduct.getName());
        productFromDB.setPrice(saveProduct.getPrice());

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        productFromDB.setCategory(category);

        return productRepository.save(productFromDB);
    }

    @Override
    public Product disableOneById(Long productId) {
        Product productFromDB = this.productRepository
                .findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));

        productFromDB.setStatus(Product.ProductStatus.DISABLE);
        return this.productRepository.save(productFromDB);
    }
}
