package com.assignment.retailerreward.repo;

import com.assignment.retailerreward.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
