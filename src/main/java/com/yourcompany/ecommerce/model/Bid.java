package com.yourcompany.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bids")
public class Bid {

    @Id
    private String id;
    private String productId;
    private String productName; // ✅ Ensure product name is stored
    private String userId;
    private String phoneNumber;
    private double bidAmount;
    private double quantity;
    private double totalAmount;
    private LocalDateTime createdAt; // ✅ Store bid timestamp

    // ✅ Constructors
    public Bid() {
        this.createdAt = LocalDateTime.now(); // ✅ Auto-set bid time
    }

    public Bid(String productId, String productName, String userId, String phoneNumber, double bidAmount, double quantity) {
        this.productId = productId;
        this.productName = productName;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.bidAmount = bidAmount;
        this.quantity = quantity;
        this.totalAmount = bidAmount * quantity;
        this.createdAt = LocalDateTime.now(); // ✅ Auto-set bid time
    }

    // ✅ Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getBidAmount() { return bidAmount; }
    public void setBidAmount(double bidAmount) { this.bidAmount = bidAmount; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}


