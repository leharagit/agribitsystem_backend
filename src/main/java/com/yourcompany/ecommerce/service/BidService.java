package com.yourcompany.ecommerce.service;

import com.yourcompany.ecommerce.model.Bid;
import java.util.List;
import java.util.Optional;

public interface BidService {
    Bid createBid(Bid bid);
    Optional<Bid> getBidById(String bidId);
    List<Bid> getAllBids();
    Bid updateBid(String bidId, Bid bid);
    void deleteBid(String bidId);
    List<Bid> getBidsByProductId(String productId);
    Optional<Bid> getMaxBidByProductId(String productId);
    Optional<String> getMaxBidderPhoneNumber(String productId);
}
