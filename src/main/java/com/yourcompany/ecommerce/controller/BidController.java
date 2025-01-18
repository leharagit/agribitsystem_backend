package com.yourcompany.ecommerce.controller;

import com.yourcompany.ecommerce.model.Bid;
import com.yourcompany.ecommerce.service.BidService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bids")
@CrossOrigin(origins = "http://localhost:5173")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public Bid createBid(@RequestBody Bid bid) {
        return bidService.createBid(bid);
    }

    @GetMapping("/{id}")
    public Optional<Bid> getBidById(@PathVariable String id) {
        return bidService.getBidById(id);
    }

    @GetMapping
    public List<Bid> getAllBids() {
        return bidService.getAllBids();
    }

    @PutMapping("/{id}")
    public Bid updateBid(@PathVariable String id, @RequestBody Bid bid) {
        return bidService.updateBid(id, bid);
    }

    @DeleteMapping("/{id}")
    public void deleteBid(@PathVariable String id) {
        bidService.deleteBid(id);
    }

    @GetMapping("/product/{productId}")
    public List<Bid> getBidsByProductId(@PathVariable String productId) {
        return bidService.getBidsByProductId(productId);
    }

    @GetMapping("/max-total")
    public Optional<Bid> getMaxTotalAmountBid() {
        return bidService.getMaxTotalAmountBid();
    }




    @GetMapping("/max-total/{productId}")
    public Optional<Bid> getMaxBidByProductId(@PathVariable String productId) {
        return bidService.getMaxBidByProductId(productId);
    }
    
}
