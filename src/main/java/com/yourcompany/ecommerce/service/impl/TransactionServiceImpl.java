package com.yourcompany.ecommerce.service.impl;

import com.yourcompany.ecommerce.model.Transaction;
import com.yourcompany.ecommerce.repository.TransactionRepository;
import com.yourcompany.ecommerce.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByBuyerId(String buyerId) {
        return transactionRepository.findByBuyerId(buyerId);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(String sellerId) {
        return transactionRepository.findBySellerId(sellerId);
    }

    @Override
    public Transaction updateTransaction(String transactionId, Transaction transaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
        if (existingTransaction.isPresent()) {
            Transaction updatedTransaction = existingTransaction.get();
            updatedTransaction.setPaymentStatus(transaction.getPaymentStatus());
            updatedTransaction.setDeliveryStatus(transaction.getDeliveryStatus());
            updatedTransaction.setDate(transaction.getDate());

            // ✅ Update Bank Details
            updatedTransaction.setBankName(transaction.getBankName());
            updatedTransaction.setAccountNumber(transaction.getAccountNumber());
            updatedTransaction.setBranch(transaction.getBranch());

            return transactionRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }
    }

    @Override
    public void deleteTransaction(String transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
        } else {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }
    }
}
