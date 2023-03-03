package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {


    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    String testForFinance;
    String testForNull;
    String testForNonExistent;

    @BeforeEach
    void setup() {
        testForNull = departmentService.getListOfDeptNamesByEmp("Uri", "Rullman");
        testForFinance = departmentService.getListOfDeptNamesByEmp("Uri", "Rullman");
        testForNonExistent = departmentService.getListOfDeptNamesByEmp("Non", "Existent");
    }

    @Test
    @DisplayName("Given a first name and last name, returns a non-null value")
    void givenValidParams_ReturnsNonNull() {
        assertTrue(testForNull != null);
    }

    @Test
    @DisplayName("Given a first name and last name, returns 'Uri,Rullman,Sales, Uri,Rullman,Customer Service'")
    void givenUriRullman_ReturnsUriRullman() {
        assertEquals("[Uri,Rullman,Sales, Uri,Rullman,Customer Service]", testForFinance);
    }

    @Test
    @DisplayName("Check getListOfDept method doesnt return null")
    void checkMethodDoesNotReturnNull() {
        assertNotNull(departmentService.getListOfDeptNamesByEmp("Uri", "Rullman"));
    }

    @Test
    @DisplayName("Check that the repository isnt null")
    void checkRepoIsNotNull() {
        assertTrue(departmentRepository != null);
    }

    @Test
    @DisplayName("Check that given a user who doesn't exist, returns 'Invalid input: Employee not found.'")
    void givenANonExistentEmp_ReturnsEmptyArray() {
        assertEquals("Invalid input: Employee not found.", testForNonExistent);
    }

    @Test
    @DisplayName("Check that method returns list greater than 0 if employee is found")
    void givenThatEmployeeExistsReturnNameAndDept() {
        assertTrue(departmentRepository.getListOfDeptsByName("Uri", "Rullman").size() > 0);
    }

    @Test
    @DisplayName("Check that method returns list with 2 elements for Uri Rullman")
    void givenThatEmployeeExistsReturnCorrectListSize() {
        assertTrue(departmentRepository.getListOfDeptsByName("Uri", "Rullman").size() == 2);
    }


}