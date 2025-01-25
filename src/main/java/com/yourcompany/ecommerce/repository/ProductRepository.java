package com.yourcompany.ecommerce.repository;

import com.yourcompany.ecommerce.model.Product;
import com.yourcompany.ecommerce.service.ProductService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByUserId(String userId); // You can define custom query methods here if necessary
}
