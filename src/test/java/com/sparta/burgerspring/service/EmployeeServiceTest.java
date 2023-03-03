package com.sparta.burgerspring.service;


import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DeptEmpRepository deptEmpRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

//    @ParameterizedTest
//    @CsvSource({
//"d009, Customer Service",
//"d005, Development",
//            "d007,Sales"
//    })
//    @DisplayName("Find DepartmentId Given Department Name")
//    public void FindDepartmentIdGivenDepartmentName(String outputDeptNum,String inputDeptName){
//        Assertions.assertEquals(outputDeptNum,departmentRepository.findByDeptName(inputDeptName).getId());
//
//    }
//@Test
//    public void FindDepartmentIdGivenDepartmentName(){
//    Assertions.assertEquals("d005",
//            departmentRepository.findByDeptName("Development").getId());
//}

@Test
    void test(){
    employeeService.getEmployeesByDateAndDepartment(LocalDate.parse())
}
}
