package com.assignment.retailerreward.controller;

import com.assignment.retailerreward.calculator.PointCalculator;
import com.assignment.retailerreward.dto.ResponseDto;
import com.assignment.retailerreward.entity.Transaction;
import com.assignment.retailerreward.repo.TransactionRepo;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class PointController {

    private final TransactionRepo transactionRepo;

    @GetMapping("/customer-id/{customerId}")
    public ResponseEntity<ResponseDto> pointCalculator(@PathVariable Integer customerId) {
        PointCalculator pointCalculator = (Float amount) -> amount > 100 ? (50 + (int) (amount - 100) * 2) : ((amount >= 50 && amount <= 100) ? ((int) (amount - 50)) : 0);
        List<Transaction> transactionList = transactionRepo.findAllByCustomerId(customerId);
        int totalPoints = transactionList.stream().map(transaction -> pointCalculator.calculateReward(transaction.getTotalPrice())).reduce(0, (m1, m2) -> m1 + m2).intValue();
        PointResponse pointResponse = PointResponse.builder()
                .customerId(customerId)
                .totalPoints(totalPoints)
                .build();
        return new ResponseEntity<>(ResponseDto
                .builder()
                .status(true)
                .message("Point calculated successfully")
                .data(pointResponse)
                .build(), HttpStatus.OK);

    }

    @Builder
    @Getter
    public static class PointResponse {
        int customerId;
        int totalPoints;
    }

}
