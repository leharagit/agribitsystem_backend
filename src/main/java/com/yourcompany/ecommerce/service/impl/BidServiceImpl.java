package com.yourcompany.ecommerce.service.impl;

import com.yourcompany.ecommerce.model.Bid;
import com.yourcompany.ecommerce.repository.BidRepository;
import com.yourcompany.ecommerce.service.BidService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    public BidServiceImpl(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public Bid createBid(Bid bid) {
        bid.setTotalAmount(bid.getBidAmount() * bid.getQuantity()); // Calculate totalAmount
        return bidRepository.save(bid);
    }

    @Override
    public Optional<Bid> getBidById(String bidId) {
        return bidRepository.findById(bidId);
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Bid updateBid(String bidId, Bid bid) {
        Optional<Bid> existingBid = bidRepository.findById(bidId);
        if (existingBid.isPresent()) {
            Bid updatedBid = existingBid.get();
            updatedBid.setBidAmount(bid.getBidAmount());
            updatedBid.setQuantity(bid.getQuantity());
            updatedBid.setTotalAmount(bid.getBidAmount() * bid.getQuantity());
            return bidRepository.save(updatedBid);
        } else {
            throw new RuntimeException("Bid not found with id: " + bidId);
        }
    }

    @Override
    public void deleteBid(String bidId) {
        if (bidRepository.existsById(bidId)) {
            bidRepository.deleteById(bidId);
        } else {
            throw new RuntimeException("Bid not found with id: " + bidId);
        }
    }

    @Override
    public List<Bid> getBidsByProductId(String productId) {
        return bidRepository.findByProductId(productId);
    }

    @Override
    public Optional<Bid> getMaxTotalAmountBid() {
        return bidRepository.findAll()
                .stream()
                .max(Comparator.comparingDouble(Bid::getTotalAmount));
    }
}
