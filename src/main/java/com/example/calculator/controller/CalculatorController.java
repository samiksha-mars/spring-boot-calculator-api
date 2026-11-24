package com.example.calculator.controller;

import com.example.calculator.dto.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calc")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public CalculationResponse add(@RequestParam double a, @RequestParam double b) {
        return new CalculationResponse("add", calculatorService.add(a, b));
    }

    @GetMapping("/subtract")
    public CalculationResponse subtract(@RequestParam double a, @RequestParam double b) {
        return new CalculationResponse("subtract", calculatorService.subtract(a, b));
    }

    @GetMapping("/multiply")
    public CalculationResponse multiply(@RequestParam double a, @RequestParam double b) {
        return new CalculationResponse("multiply", calculatorService.multiply(a, b));
    }

    @GetMapping("/divide")
    public CalculationResponse divide(@RequestParam double a, @RequestParam double b) {
        return new CalculationResponse("divide", calculatorService.divide(a, b));
    }

    @GetMapping("/power")
    public CalculationResponse power(@RequestParam double base, @RequestParam double exponent) {
        return new CalculationResponse("power", calculatorService.power(base, exponent));
    }

    @GetMapping("/sqrt")
    public CalculationResponse sqrt(@RequestParam double value) {
        return new CalculationResponse("sqrt", calculatorService.sqrt(value));
    }
}
