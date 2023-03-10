package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.Employee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @ParameterizedTest
    @ValueSource(ints = {469711,250904,203062})
    @DisplayName("given the last name Zykh return " +
            "the list of employees containing employees " +
            "with id 469711,250904, 203062")
    void givenLastnameReturnEmployeesListContainCertainEmployees(int id){
        assertTrue(employeeRepository.findByLastName("Zykh").contains(employeeRepository.getEmployeeById(id)));
    }

    @ParameterizedTest
    @ValueSource(ints = {206140,61761,456243})
    @DisplayName("given the first name Aamer return " +
            "the list of employees containing employees " +
            "with id 206140,61761,456243")
    void givenFirstnameReturnEmployeesListContainCertainEmployees(int id){
        assertTrue(employeeRepository.findByFirstName("Aamer").contains(employeeRepository.getEmployeeById(id)));
    }
    @ParameterizedTest
    @CsvSource({
            "206140, Aamer, Gadepally",
            "10002, Bezalel, Simmel"
    })
    @DisplayName("given the name return Aamer Gadepally" +
            "return the list of employees containing employees " +
            "with id 206140")
    void givenFirstnameAndLastNameReturnEmployeesListContainCertainEmployees(int id, String firstName,String lastName){
        assertTrue(employeeRepository.findByFirstNameAndAndLastName(firstName,lastName).contains(employeeRepository.getEmployeeById(id)));
    }
}
