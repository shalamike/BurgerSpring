package com.sparta.burgerspring.model.repositories;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
//    @Autowired
//    DepartmentRepository departmentRepository;
//
//
//    @BeforeAll
//    void setupAll() {
//        Department department1=new Department();
//
//
//    }
}
