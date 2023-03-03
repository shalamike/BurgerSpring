package com.sparta.burgerspring.service;


import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FindEmployeeServiceTest {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DeptEmpRepository deptEmpRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeesService employeesService;

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
    public void FindDepartmentGivenDepartmentName() {
        String deptName = "Development";
        Department developmentDepartment = new Department();
        developmentDepartment.setId("d005");
        developmentDepartment.setDeptName("Development");
        Assertions.assertEquals(developmentDepartment.toString(),
                departmentRepository.findByDeptName("Development").toString()
        );
    }
}
