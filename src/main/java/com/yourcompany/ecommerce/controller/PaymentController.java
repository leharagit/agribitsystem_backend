package com.yourcompany.ecommerce.controller;

import com.yourcompany.ecommerce.model.Payment;
import com.yourcompany.ecommerce.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = {"http://localhost:5173", "https://officialagribit.netlify.app"})
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${stripe.secret.key}") // Load Stripe Secret Key from application.properties
    private String stripeSecretKey;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // ✅ Create a payment entry
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    // ✅ Get a payment by ID
    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable String id) {
        return paymentService.getPaymentById(id);
    }

    // ✅ Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // ✅ Update a payment
    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable String id, @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    // ✅ Delete a payment
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable String id) {
        paymentService.deletePayment(id);
    }

    // ✅ Create a Stripe Payment Intent
    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> request) {
        Map<String, String> response = new HashMap<>();
        try {
            double totalAmount = Double.parseDouble(request.get("totalAmount").toString());

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) (totalAmount * 100)) // Convert to cents
                    .setCurrency("usd")
                    .setPaymentMethodTypes(List.of("card"))
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);
            response.put("clientSecret", intent.getClientSecret());
        } catch (StripeException e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}
