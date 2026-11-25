package com.example.calculator.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
        "/api/calc/add?a=2&b=3, add, 5.0",
        "/api/calc/subtract?a=10&b=4, subtract, 6.0",
        "/api/calc/multiply?a=6&b=7, multiply, 42.0",
        "/api/calc/divide?a=10&b=4, divide, 2.5",
        "/api/calc/power?base=2&exponent=8, power, 256.0",
        "/api/calc/sqrt?value=144, sqrt, 12.0"
    })
    void computesEachOperation(String url, String operation, double result) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value(operation))
                .andExpect(jsonPath("$.result").value(result));
    }

    @Test
    void divideByZeroReturns400() throws Exception {
        mockMvc.perform(get("/api/calc/divide").param("a", "1").param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Invalid input"))
                .andExpect(jsonPath("$.detail").value("Cannot divide by zero"));
    }

    @Test
    void squareRootOfNegativeReturns400() throws Exception {
        mockMvc.perform(get("/api/calc/sqrt").param("value", "-4"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("Cannot compute square root of a negative number"));
    }

    @Test
    void missingOrNonNumericParameterReturns400() throws Exception {
        mockMvc.perform(get("/api/calc/add").param("a", "1"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(get("/api/calc/add").param("a", "1").param("b", "two"))
                .andExpect(status().isBadRequest());
    }
}
