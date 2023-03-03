package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Test
    void getAvgSalByDeptNameAndDate() {
    }

    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    String testForFinance;
    String testForNull;

    @BeforeEach
    void setup() {
        testForNull = departmentService.getListOfDeptNamesByEmp("Uri", "Rullman");
        testForFinance = departmentService.getListOfDeptNamesByEmp("Uri", "Rullman");
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
    void checkMethodDoesNotReturnNull(){
        assertNotNull(departmentService.getListOfDeptNamesByEmp("Uri", "Rullman"));
    }

    @Test
    @DisplayName("Check that the repository isnt null")
    void checkRepoIsNotNull(){
        assertTrue(departmentRepository != null);
    }

    @Test
    @DisplayName("find department given department name called Testing")
    public void findDepartmentGivenDepartmentName() {
        String deptName = "Development";
        Department developmentDepartment = new Department();
        developmentDepartment.setId("d010");
        developmentDepartment.setDeptName("Testing");
        departmentRepository.save(developmentDepartment);
        assertEquals(
                departmentRepository.findByDeptName("Testing"),developmentDepartment
        );
        departmentRepository.deleteById("d010");
    }
}