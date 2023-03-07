package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    private final SalaryService salaryService;

    private ObjectMapper objectMapper;

    @Autowired
    public SalaryController(SalaryService salaryService, ObjectMapper objectMapper){
        this.salaryService = salaryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/salary/{id}")//this will return a single employee salary
    public ResponseEntity<String> getSalaryById(@RequestParam Integer id){
        return null;
    }

    @GetMapping(value = "/salaries")//this will return a list of employees
    public ResponseEntity<String> getEmployeesEarningAboveSalary(Integer salary){
        return null;
    }
}
