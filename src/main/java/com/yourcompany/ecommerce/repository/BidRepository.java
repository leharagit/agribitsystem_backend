package com.yourcompany.ecommerce.repository;

import com.yourcompany.ecommerce.model.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BidRepository extends MongoRepository<Bid, String> {
    List<Bid> findByProductId(String productId);
}
