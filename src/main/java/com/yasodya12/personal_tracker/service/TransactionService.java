package com.yasodya12.personal_tracker.service;

import com.yasodya12.personal_tracker.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByUserId(Long userId);
    List<Transaction> getTransactionsByCategoryId(Long categoryId);
    void deleteTransaction(Long transactionId);
    double getMonthlySummary(Long userId, int month, int year);
}
