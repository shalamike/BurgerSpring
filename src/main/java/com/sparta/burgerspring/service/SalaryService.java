package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.repositories.SalaryRepository;
import com.sparta.burgerspring.model.repositories.TitleRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SalaryService {
    private final SalaryRepository salaryRepository;
    @Autowired
    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public String genderPaygap(String Department){
        double femaleSalary = 0;
        double maleSalary = 0;
        double payGapPercentage = 0.0;
        String department = "";
        if(Department.toLowerCase().equals("all")){
            department = "all departments";
            femaleSalary = salaryRepository.findSalaryByGender("F");
            maleSalary = salaryRepository.findSalaryByGender("M");
            payGapPercentage = (maleSalary/femaleSalary)-1;
        }else{
            department = Department;
            femaleSalary = salaryRepository.findSalaryByGenderAndDept("F",Department);
            maleSalary = salaryRepository.findSalaryByGenderAndDept("M",Department);
            payGapPercentage = (maleSalary/femaleSalary)-1;
        }
        return "The gender paygap in "+department+" is " + payGapPercentage*100 + " percent";
    }
}
