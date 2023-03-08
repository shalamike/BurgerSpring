package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.service.EmployeeService;
import com.sparta.burgerspring.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SalaryController {

    private final SalaryService salaryService;
    private final EmployeeService employeeService;

    private ObjectMapper objectMapper;

    @Autowired
    public SalaryController(SalaryService salaryService, EmployeeService employeeService, ObjectMapper objectMapper){
        this.salaryService = salaryService;
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/salary/{id}")//this will return a single employee's highest salary
    public ResponseEntity<String> getSalaryById(@PathVariable Integer id){
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        Integer employeesSalary =salaryService.getEmployeeHighestSalaryByEmployeeId(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(employee.isPresent()){
            ResponseEntity<String> employeePresentResponse = null;
            employeePresentResponse = new ResponseEntity<>(
                    "{"+ employee.get().getId() + ", " + employee.get().getFirstName() + " " + employee.get().getLastName()  + " : " + employeesSalary + "}",
                    httpHeaders,
                    HttpStatus.OK);
            return employeePresentResponse;
        } else {
            ResponseEntity<String> employeNotFound = new ResponseEntity<>(
                    "{\"message\" : \"That employee doesn't exist\"}",
                    httpHeaders,
                    HttpStatus.NOT_FOUND
            );
            return employeNotFound;
        }
    }

    @GetMapping(value = "/salaries/{salary}")//this will return a list of employees
    public ResponseEntity<String> getEmployeesEarningAboveSalary(@PathVariable Integer salary){
        List<Employee> employees = salaryService.getEmployeeEarningAboveGivenSalary(salary);
        ArrayList<String> employeesAndSalaries = new ArrayList<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        for (Employee employee: employees) {
            employeesAndSalaries.add( employee.getId() +
                    " " + employee.getFirstName() +
                    " " + employee.getLastName() +
                    " : " +
                    salaryService.getEmployeeHighestSalaryByEmployeeId(employee.getId()));
        }
        if(employees.size()>0){
            ResponseEntity<String>employeesAndSalariesResponse = new ResponseEntity<>(
                    "{" + " All employees earning above " + salary + ":" + employeesAndSalaries + "}",
                    httpHeaders,
                    HttpStatus.OK
            );
            return employeesAndSalariesResponse;
        }
        ResponseEntity<String> noEmployeesFound = new ResponseEntity<>(
                "{\"message\" : \"no Employees exist\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND
        );
        return noEmployeesFound;
    }
}
