package com.yourcompany.ecommerce.service;

import com.yourcompany.ecommerce.model.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(String transactionId);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByBuyerId(String buyerId);
    List<Transaction> getTransactionsBySellerId(String sellerId);
    Transaction updateTransaction(String transactionId, Transaction transaction);
    void deleteTransaction(String transactionId);
}
