package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double add(double a, double b) {
        return requireFinite(a + b);
    }

    public double subtract(double a, double b) {
        return requireFinite(a - b);
    }

    public double multiply(double a, double b) {
        return requireFinite(a * b);
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return requireFinite(a / b);
    }

    public double power(double base, double exponent) {
        return requireFinite(Math.pow(base, exponent));
    }

    public double sqrt(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot compute square root of a negative number");
        }
        return requireFinite(Math.sqrt(value));
    }

    /** Rejects NaN and infinite results (overflow, NaN input, or e.g. a fractional power of a negative base). */
    private static double requireFinite(double result) {
        if (!Double.isFinite(result)) {
            throw new IllegalArgumentException("Result is not a finite number");
        }
        return result;
    }
}
