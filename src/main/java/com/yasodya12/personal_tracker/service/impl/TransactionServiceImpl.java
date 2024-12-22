package com.yasodya12.personal_tracker.service.impl;

import com.yasodya12.personal_tracker.entity.Transaction;
import com.yasodya12.personal_tracker.repositry.TransactionRepo;
import com.yasodya12.personal_tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    private TransactionRepo transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserUserId(userId);
    }

    @Override
    public List<Transaction> getTransactionsByCategoryId(Long categoryId) {
        return transactionRepository.findByCategoryCategoryId(categoryId);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public double getMonthlySummary(Long userId, int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
//        return transactionRepository.getMonthlySummary(userId, yearMonth.atDay(1), yearMonth.atEndOfMonth());

        return 1.0;
    }
}
