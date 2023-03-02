package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeptEmpService {
    private DeptEmpRepository deptEmpRepository;

    @Autowired
    public DeptEmpService(DeptEmpRepository deptEmpRepository) {
        this.deptEmpRepository = deptEmpRepository;
    }

    public String getSizeOfDepartmentInGivenYear(String deptName, LocalDate fromDate, LocalDate toDate){
        return "The size of " + deptName + " is " + deptEmpRepository.getSizeOfDepartment(deptName, fromDate, toDate).toString();
    }
}
