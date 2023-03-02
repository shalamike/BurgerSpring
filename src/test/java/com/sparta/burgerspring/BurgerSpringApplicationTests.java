package com.sparta.burgerspring;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

@SpringBootTest
class BurgerSpringApplicationTests {
    @MockBean
    DepartmentRepository departmentRepository;
    @MockBean
    DeptEmpRepository deptEmpRepository;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {

    }

}
