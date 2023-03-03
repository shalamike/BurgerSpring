package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public String getAvgSalByDeptNameAndDate(String deptName, LocalDate date) {
        List<String> depts = new ArrayList<>(List.of("Customer Service", "Development", "Finance", "Human Resources", "Marketing", "Production", "Quality Management", "Research", "Sales"));
        LocalDate currentDate = LocalDate.now();
        if (deptName == null || date == null) {
            throw new NullPointerException("Arguments can't be null");
        }


        if (depts.contains(deptName)){
            if (date.isBefore(LocalDate.of(1985,1,1)) || date.isAfter(currentDate)) {
                return "Invalid date selection: Please enter a date from 1985-01-01 to " + currentDate + ".";
            } else {
                return "Average salary for the " + deptName + " department " + "during '" + date + "' is " + departmentRepository.getListOfSalariesByDept(deptName, date);
            }
        }
        else{
            return "Department '" + deptName + "' not present in Database";
        }


    }

    public String getListOfDeptNamesByEmp(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new NullPointerException("Arguments can't be null");
        }
        if (departmentRepository.getListOfDeptsByName(firstName, lastName).size() > 0) {
            return departmentRepository.getListOfDeptsByName(firstName, lastName).toString();
        } else {
            return "Invalid input: Employee not found.";
        }
    }
}
