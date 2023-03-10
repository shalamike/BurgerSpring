package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.service.EmployeeService;
import com.sparta.burgerspring.service.SalaryService;
import com.sparta.burgerspring.service.TitleService;
import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TitleController {

    private final TitleService titleService;

    private final SalaryService salaryService;

    private final EmployeeService employeeService;

    private ObjectMapper objectMapper;

    @Autowired
    public TitleController(TitleService titleService, SalaryService salaryService, EmployeeService employeeService, ObjectMapper objectMapper) {
        this.titleService = titleService;
        this.salaryService = salaryService;
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/title/{title}/{year}")
    public ResponseEntity<String> getSalariesByGivenJobTitleByYear( @PathVariable String title, @PathVariable String year){
        List<Salary> salaries = titleService.getSalariesInYearByJobTitle(title, year);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        ArrayList<String> employeesAndSalaries = new ArrayList<>();
        for (Salary salary: salaries) {
            Optional<Employee> emp = employeeService.findEmployeeById(salary.getEmpNo().getId());
            employeesAndSalaries.add(emp.get().getId() + ":" + emp.get().getFirstName() + " " + emp.get().getLastName() +  ":" + salary.getSalary() );
        }
        if(salaries != null && salaries.size() > 0){
            ResponseEntity<String> salaryRangeResponse = new ResponseEntity<>("{" + title + ":" + employeesAndSalaries + "}", httpHeaders, HttpStatus.OK);
            return salaryRangeResponse;
        }else{
            return new ResponseEntity<>(
                    "{\"message\" : \"That title  doesn't exist or year isnt valid\"}",
                    httpHeaders,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(value = "/titleRange/{title}/{year}")
    public ResponseEntity<String> getSalaryRangeByJobTitleInGivenYear( @PathVariable String title, @PathVariable String year){
        List<Integer> salaryRange = titleService.getSalaryRangeInYearByJobTitle(title, year);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        ArrayList<String> employeesAndSalaries = new ArrayList<>();
        if(salaryRange != null || salaryRange.size() == 2){
            ResponseEntity<String> salaryRangeResponse = new ResponseEntity<>("{" + title + ":" + salaryRange + "}", httpHeaders, HttpStatus.OK);
            return salaryRangeResponse;
        }else{
            return new ResponseEntity<>(
                    "{\"message\" : \"That title  doesn't exist or year isnt valid\"}",
                    httpHeaders,
                    HttpStatus.NOT_FOUND
            );
        }
    }


}
