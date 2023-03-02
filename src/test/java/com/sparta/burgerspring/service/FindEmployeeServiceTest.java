package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.model.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FindEmployeeServiceTest {
    @MockBean
    DepartmentRepository departmentRepository;
    @MockBean
    DeptEmpRepository deptEmpRepository;
    @MockBean
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeesService employeesService;

    @Test
    @DisplayName("<unnamed>")
    public void <unnamed>(){
        
    
    }

}
