package com.sparta.burgerspring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeptEmpServiceTest {


    @Autowired
    DeptEmpService deptEmpService;

    @Test
    @DisplayName("Given department finance, between 1990-01-01 and 1990-12-31, returns size of 15")
    void givenDepartmentFinanceBetweenGivenYear_ReturnsSizeOf15() {
        assertEquals("The size of finance is 15", deptEmpService.getSizeOfDepartmentInGivenYear("finance", LocalDate.of(1990,01,01), LocalDate.of(1990, 12, 31)));
    }
}