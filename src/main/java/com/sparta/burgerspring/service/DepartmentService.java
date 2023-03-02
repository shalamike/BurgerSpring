package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }



    public String getAvgSalByDeptNameAndDate(String deptName, LocalDate date){
        return "Average salary for the " + deptName + " department " + "during '" + date + "' is " + departmentRepository.getListOfSalariesByDept(deptName, date);
    }

    public String getListOfDeptNamesByEmp(String firstName, String lastName){
        return departmentRepository.getListOfDeptsByName(firstName, lastName).toString();
    }
}
