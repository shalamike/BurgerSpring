package com.sparta.burgerspring.service;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.model.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service

public class SalaryService {
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public String genderPaygap(String Department){
        double femaleSalary = 0;
        double maleSalary = 0;
        double payGapPercentage = 0.0;
        String department = "";
        List<Department> departments = departmentRepository.findAll();
        List<String> departmentNames = new ArrayList<>();
        for(Department depts: departments){
            departmentNames.add(depts.getDeptName().toLowerCase(Locale.ROOT));
        }
        if(Department.toLowerCase().equals("all")){
            department = "all departments";
            femaleSalary = salaryRepository.findSalaryByGender("F");
            maleSalary = salaryRepository.findSalaryByGender("M");
            payGapPercentage = (maleSalary/femaleSalary)-1;
        }else if(departmentNames.contains(Department.toLowerCase())){
            department = Department;
            femaleSalary = salaryRepository.findSalaryByGenderAndDept("F",Department);
            maleSalary = salaryRepository.findSalaryByGenderAndDept("M",Department);
            payGapPercentage = (maleSalary/femaleSalary)-1;
        }else {
            departmentNames.add("all");
         return "That Department does not exist, possible parameters are; \n"+ departmentNames;
        }
        return "The gender paygap from male to female in "+department+" is " + payGapPercentage*100 + " percent";
    }

    public List<Employee>getEmployeeEarningAboveGivenSalary(int salary){

        return salaryRepository.findSalariesAboveCertainSalary(salary);
    }

    public Integer getEmployeeHighestSalaryByEmployeeId(Integer id){
        return salaryRepository.highestSalaryOfGivenEmployeeId(id);
    }

}
