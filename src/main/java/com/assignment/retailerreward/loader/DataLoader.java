package com.assignment.retailerreward.loader;

import com.assignment.retailerreward.entity.Customer;
import com.assignment.retailerreward.entity.Transaction;
import com.assignment.retailerreward.repo.CustomerRepo;
import com.assignment.retailerreward.repo.TransactionRepo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    private final CustomerRepo customerRepo;
    private final TransactionRepo transactionRepo;

    public DataLoader(CustomerRepo customerRepo, TransactionRepo transactionRepo) {
        this.customerRepo = customerRepo;
        this.transactionRepo = transactionRepo;
    }

    @PostConstruct
    public void load() {
        loadCustomer();
    }

    private void loadCustomer() {
        Customer ironman = new Customer(null, "ironman", "8147851654", "ironman@yopmail.com");
        Customer hulk = new Customer(null, "hulk", "8147851655", "hulk@yopmail.com");
        Customer thor = new Customer(null, "thor", "8147851656", "thor@yopmail.com");
        ironman = customerRepo.save(ironman);
        loadTransaction(ironman, 120F);
        hulk = customerRepo.save(hulk);
        loadTransaction(hulk, 90F);
        thor = customerRepo.save(thor);
        loadTransaction(thor, 40F);
    }

    private void loadTransaction(Customer customer, Float amount) {
        Transaction transaction = new Transaction(null, customer, amount);
        transactionRepo.save(transaction);
    }
}
