package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.service.SalaryService;
import org.junit.jupiter.api.*;
import org.mockito.internal.runners.RunnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalaryRepositoryTest {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("testing if given id of 10032 eanred a max of 69539")
    void testEmployeeEarnsMaxOf69539(){
        assertTrue(salaryRepository.highestSalaryOfGivenEmployeeId(10032) == 69539);
    }


}