package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeByLastName(String lastName){
        return employeeRepository.findByLastName(lastName);
    }

}
