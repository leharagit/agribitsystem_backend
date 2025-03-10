package com.yourcompany.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private String transactionId;
    private String userId;
    private double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime timestamp;

    // ✅ Constructor
    public Payment() {
        this.timestamp = LocalDateTime.now();
    }

    public Payment(String transactionId, String userId, double totalAmount, String paymentMethod, String paymentStatus) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.timestamp = LocalDateTime.now();
    }

    // ✅ Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
