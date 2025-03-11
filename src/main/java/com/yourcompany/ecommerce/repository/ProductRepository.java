package com.yourcompany.ecommerce.repository;

import com.yourcompany.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByUserId(String userId);

    // ✅ Fetch products where startBidPrice >= given value
    @Query("{ 'startBidPrice': { $gte: ?0 } }")
    List<Product> findByStartBidPriceGreaterThanEqual(double startBidPrice);
}
