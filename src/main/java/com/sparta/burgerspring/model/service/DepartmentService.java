package com.sparta.burgerspring.model.service;

import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.SalaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }



    public String getAvgSalByDeptNameAndDate(String deptName, LocalDate date){
        return "Average salary for the " + deptName + " department " + "during '" + date + "' is " + departmentRepository.getListOfSalariesByDept(deptName, date);
    }
}
