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
    String[] Departments = {"Customer Service", "Development", "Finance", "Human Resources", "Marketing", "Production", "Quality Management", "Research", "Sales"};
    @Test
    @DisplayName("testing if given id of 10032 earned a max of 69539")
    void testEmployeeEarnsMaxOf69539(){
        assertTrue(salaryRepository.highestSalaryOfGivenEmployeeId(10032) == 69539);
    }

    @Test
    @DisplayName("Test return of all male employees average salary is not Zero")
    void returnAllMaleSalaryNotZero(){
        assertTrue(salaryRepository.findSalaryByGender("M")!=0.0);
    }
    @Test
    @DisplayName("Test return of all female employees average salary is not Zero")
    void returnAllFemaleSalaryNotZero(){
        assertTrue(salaryRepository.findSalaryByGender("F")!=0.0);
    }
    @Test
    @DisplayName("Test return of female employees in each department average salary is not Zero")
    void returnEachDeptFemaleSalaryNotZero(){
        for(String s: Departments) {
            assertTrue(salaryRepository.findSalaryByGenderAndDept("F",s) != 0.0);
        }
    }
    @DisplayName("Test return of male employees in each department average salary is not Zero")
    void returnEachDeptMaleSalaryNotZero(){
        for(String s: Departments) {
            assertTrue(salaryRepository.findSalaryByGenderAndDept("M",s) != 0.0);
        }
    }
    @Test
    @DisplayName("Test return of all male employees average salary is not Zero")
    void returnAllMaleSalaryMoreThanZero(){
        assertTrue(salaryRepository.findSalaryByGender("M")>0.0);
    }
    @Test
    @DisplayName("Test return of all female employees average salary is not Zero")
    void returnAllFemaleSalaryMoreThanZero(){
        assertTrue(salaryRepository.findSalaryByGender("F")>0.0);
    }
    @Test
    @DisplayName("Test return of female employees in each department average salary is not Zero")
    void returnEachDeptFemaleSalaryMoreThanZero(){
        for(String s: Departments) {
            assertTrue(salaryRepository.findSalaryByGenderAndDept("F",s) > 0.0);
        }
    }
    @DisplayName("Test return of male employees in each department average salary is not Zero")
    void returnEachDeptMaleSalaryMoreThanZero(){
        for(String s: Departments) {
            assertTrue(salaryRepository.findSalaryByGenderAndDept("M",s) > 0.0);
        }
    }
}
