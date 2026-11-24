package com.example.calculator.dto;

public record CalculationResponse(
        String operation,
        double result
) {
}
