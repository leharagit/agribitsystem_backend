package com.yourcompany.ecommerce.repository;

import com.yourcompany.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByUserId(String userId);

    // ✅ Fetch products where startBidPrice is between min & max
    @Query("{ 'startBidPrice': { $gte: ?0, $lte: ?1 } }")
    List<Product> findByStartBidPriceBetween(double minBidPrice, double maxBidPrice);
}
