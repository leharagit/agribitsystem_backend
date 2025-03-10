package com.yourcompany.ecommerce.service;

import com.yourcompany.ecommerce.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Optional<Payment> getPaymentById(String paymentId);
    List<Payment> getAllPayments();
    Payment updatePayment(String paymentId, Payment payment);
    void deletePayment(String paymentId);
}
