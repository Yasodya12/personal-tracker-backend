package com.yasodya12.personal_tracker.repositry;

import com.yasodya12.personal_tracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface TransactionRepo extends JpaRepository<Transaction, Long> {


  @Query("SELECT SUM(t.amount) FROM Transaction t " +
          "WHERE t.user.userId = :userId " +
          "AND t.transactionDate BETWEEN :startDate AND :endDate")
  Double getMonthlySummary(@Param("userId") Long userId,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate);

//  List<Transaction> findByUserId(Long userId);

  List<Transaction> findByUserUserId(Long userId);

  List<Transaction> findByCategoryCategoryId(Long categoryId);
}
