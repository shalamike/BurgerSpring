package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.repositories.SalaryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SalaryServiceTest {
   @Autowired
   SalaryService salaryService;
//    @MockBean
//   SalaryRepository salaryRepository;
    String outputAll;
    String outputDevelopment;
    @BeforeEach
    void setup() {
        outputAll = salaryService.genderPaygap("All");
        outputDevelopment = salaryService.genderPaygap("Development");
    }
    @Test
    @DisplayName("Test the check all departments to return as a non-null value")
    void testForNonNullAll(){

    assertTrue(outputAll!=null);
}
@Test
    @DisplayName("Test a single department returns a non-null value")
    void testForNonNullOne(){
        assertTrue(outputDevelopment!=null);
}
}