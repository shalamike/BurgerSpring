package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {


    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    String testForFinance;
    String testForNotNull;
    String testForNonExistent;
    LocalDate currentDate;

    @BeforeEach
    void setup() {
        testForNotNull = departmentService.getListOfDeptNamesByEmp("Uri", "Rullman");
        testForFinance = departmentService.getListOfDeptNamesByEmp("Uri", "Rullman");
        testForNonExistent = departmentService.getListOfDeptNamesByEmp("Non", "Existent");
        currentDate = LocalDate.now();
    }

    @Test
    @DisplayName("Given a first name and last name, returns a non-null value")
    void givenValidParams_ReturnsNonNull() {
        assertTrue(testForNotNull != null);
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
    @DisplayName("Check that exception is thrown if parameters are null for getListOfDeptsByName")
    void givenThatparametersAreNullThrowException() {
        assertThrowsExactly(NullPointerException.class, () -> departmentService.getListOfDeptNamesByEmp(null, null));
    }

    @Test
    @DisplayName("Check that correct Department name is given in getAvgSalByDeptNameAndDate method")
    void givenThatDeptNameIsIncorrectReturnAppropriateString() {
        assertEquals("Department 'Engineering' not present in Database", departmentService.getAvgSalByDeptNameAndDate("Engineering", LocalDate.of(2000,01,01)));
    }

    @Test
    @DisplayName("Check that NullPointerException is thrown if an argument is null in getAvgSalByDeptNameAndDate method")
    void givenThatArgIsNullThrowException() {
        assertThrowsExactly(NullPointerException.class, () -> departmentService.getAvgSalByDeptNameAndDate(null, null));
    }

    @ParameterizedTest
    @CsvSource({"1980-01-01", "2024-01-01"})
    @DisplayName("Check that date given is in the past and after hire date of first employee in getAvgSalByDeptNameAndDate method")
    void givenThatDateIsIncorrectReturnAppropriateString(LocalDate date) {
        assertEquals("Invalid date selection: Please enter a date from 1985-01-01 to " + currentDate + ".", departmentService.getAvgSalByDeptNameAndDate("Finance", date));
    }

    @Test
    @DisplayName("Check that data is returned if correct date and dept name is given in getAvgSalByDeptNameAndDate method")
    void givenThatArgsAreValidReturnData() {
        assertEquals("Average salary for the Sales department during '2000-01-01' is 83416.7841", departmentService.getAvgSalByDeptNameAndDate("Sales", LocalDate.of(2000,01,01)));
    }




}