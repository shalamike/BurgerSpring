package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final DeptEmpRepository deptEmpRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DeptEmpRepository deptEmpRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.deptEmpRepository = deptEmpRepository;
    }

    public ArrayList<Employee> getEmployeesByDateAndDepartment(LocalDate beofreDate, LocalDate afterDate, String deptName){
        Department dept=departmentRepository.findByDeptName(deptName);
        ArrayList<Employee> employees=new ArrayList<Employee>();
        for(DeptEmp deptEmp: deptEmpRepository.findByFromDateIsBeforeAndToDateAfterAndDeptNo( beofreDate,  afterDate, dept)){
            Integer employeeId=deptEmp.getId().getEmpNo();
            employees.add(employeeRepository.getEmployeeById(employeeId));
        };
        return employees;
    }

}
