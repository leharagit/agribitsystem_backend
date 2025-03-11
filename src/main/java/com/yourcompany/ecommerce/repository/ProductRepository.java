package com.yourcompany.ecommerce.repository;

import com.yourcompany.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByUserId(String userId);
   

    // ✅ Find products within a bid price range
    @Query("{ 'startBidPrice': { $gte: ?0, $lte: ?1 } }")
    List<Product> findByStartBidPriceBetween(double minBidPrice, double maxBidPrice);

    // ✅ Find products by category
    List<Product> findByCategory(String category);

    // ✅ Find products by category and bid price range
    @Query("{ 'category': ?2, 'startBidPrice': { $gte: ?0, $lte: ?1 } }")
    List<Product> findByStartBidPriceBetweenAndCategory(double minBidPrice, double maxBidPrice, String category);
}
