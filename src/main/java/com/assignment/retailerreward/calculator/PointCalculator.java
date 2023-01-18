package com.assignment.retailerreward.calculator;

@FunctionalInterface
public interface PointCalculator {
    Integer calculateReward(Float amount);
}
