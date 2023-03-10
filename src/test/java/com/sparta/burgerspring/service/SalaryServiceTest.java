package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.model.repositories.SalaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SalaryServiceTest {
   @Autowired
   SalaryService salaryService;

   @Autowired
   EmployeeRepository employeeRepository;
//    @MockBean
//   SalaryRepository salaryRepository;
    String outputAll;
    String outputDevelopment;

    List<Employee> employeeListEarningAboveSalary;
    @Autowired
    private SalaryRepository salaryRepository;
@Autowired
    private DepartmentRepository departmentRepository;

private List<String> departmentNames = new ArrayList<>();
    @BeforeEach
    void setup() {
        outputAll = salaryService.genderPaygap("All");
        outputDevelopment = salaryService.genderPaygap("Development");
        List<Department> departments = departmentRepository.findAll();
        employeeListEarningAboveSalary = salaryService.getEmployeeEarningAboveGivenSalary(40000);

        for(Department depts: departments){
            departmentNames.add(depts.getDeptName().toLowerCase(Locale.ROOT));
        }
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

    @Test
    @DisplayName("testing getEmployeesWhoEarnsAboveGivenSalary Returns a value")
    void testForNonNullValueForGivenSalary(){
        assertTrue(employeeListEarningAboveSalary != null);
    }
    @Test
    @DisplayName("testing genderPaygap if statement sets the department to all departments on the parameter all")
    void testForAllPaygapParameterWorking(){
        assertTrue(salaryService.genderPaygap("All").contains("all departments"));
    }
    @ParameterizedTest
    @DisplayName("testing genderPaygap all ignores case")
    @ValueSource(strings = {"All","ALl","ALL","AlL","all","alL","aLl","aLL"})
    void testForAllPaygapParameterIgnoringCase(String s){
        assertTrue(salaryService.genderPaygap(s).contains("all departments"));
    }
    @Test
    @DisplayName("testing genderPaygap if statement sets the department to the same department as the parameter")
    void testForEachPaygapParameterWorking(){
        for(String s: departmentNames) {
            assertTrue(salaryService.genderPaygap(s).contains(s));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {10001, 10004, 10027})
    @DisplayName("testing getEmployeesWhoEarnsAboveGivenSalary Returns all values greater then 40000")
    void testEmployeesWhoEarnAboveGivenSalaryExistsWithinData(int id){
        assertTrue(employeeListEarningAboveSalary.contains(employeeRepository.getEmployeeById(id)));
    }



    @Test
    @DisplayName("testing if given id of 10032 earned a max of 69539")
    void testEmployeeEarnsMaxOf69539(){
        assertTrue(salaryService.getEmployeeHighestSalaryByEmployeeId(10032) == 69539);
    }
}