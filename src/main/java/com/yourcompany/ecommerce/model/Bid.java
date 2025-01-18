package com.yourcompany.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bids") // This annotation maps the class to a MongoDB collection
public class Bid {

    @Id // Marks the field as the primary key for MongoDB document
    private String id;

    private String productId; // Foreign Key to Product
    private String userId; // Foreign Key to User (Buyer)
    private double bidAmount; // The amount the user bids
    private double quantity; // The quantity of the product bid
    private double totalAmount; // The calculated total amount (bidAmount * quantity)

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", bidAmount=" + bidAmount +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
