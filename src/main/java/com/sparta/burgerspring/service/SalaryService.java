package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.SalaryId;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.model.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class SalaryService {
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
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

    public List<Employee>getEmployeeEarningAboveGivenSalary(int salary){

        return salaryRepository.findSalariesAboveCertainSalary(salary);
    }

    public Integer getEmployeeHighestSalaryByEmployeeId(Integer id){
        return salaryRepository.highestSalaryOfGivenEmployeeId(id);
    }

    public Optional<Salary> getSalaryByEmpIdAndFromDate(Integer id, LocalDate fromDate){
        SalaryId salaryId = new SalaryId();
        salaryId.setFromDate(fromDate);
        salaryId.setEmpNo(id);
        return salaryRepository.findById(salaryId);
    }

    public void saveSalary(Salary salary){
        salaryRepository.save(salary);
    }

}
