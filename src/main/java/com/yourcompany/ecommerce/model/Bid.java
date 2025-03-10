package com.yourcompany.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bids")
public class Bid {

    @Id
    private String id;
    private String productId;
    private String userId;
    private String phoneNumber; // New field for phone number
    private double bidAmount;
    private double quantity;
    private double totalAmount;

    // Constructors
    public Bid() {}

    public Bid(String productId, String userId, String phoneNumber, double bidAmount, double quantity) {
        this.productId = productId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.bidAmount = bidAmount;
        this.quantity = quantity;
        this.totalAmount = bidAmount * quantity;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

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

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bidAmount=" + bidAmount +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                '}';
    }
}


