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
        if (firstName == null || lastName == null) {
            throw new NullPointerException("Argument can't be null");
        }
        if (departmentRepository.getListOfDeptsByName(firstName, lastName).size() > 0) {
            return departmentRepository.getListOfDeptsByName(firstName, lastName).toString();
        } else {
            return "Name not present in Database";
        }
    }
}
