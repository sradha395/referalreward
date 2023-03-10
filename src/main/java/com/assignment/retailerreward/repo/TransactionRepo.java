package com.assignment.retailerreward.repo;

import com.assignment.retailerreward.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    @Query(value = "select * from transaction where customer_id =?1", nativeQuery = true)
    List<Transaction> findAllByCustomerId(Integer customerId);
}
