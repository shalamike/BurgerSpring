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
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
            return new ResponseEntity<>(
                    "{\"message\" : \"That employee doesn't exist\"}",
                    httpHeaders,
                    HttpStatus.NOT_FOUND
            );
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
            return new ResponseEntity<>(
                    "{" + salary + ":" + employeesAndSalaries + "}",
                    httpHeaders,
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "{\"message\" : \"no Employees exist\"}",
                httpHeaders,
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping(value = "/salary/{empId}/{fromDate}/{newSalary}")
    ResponseEntity<String> salaryToUpdate(@PathVariable int empId,@PathVariable String fromDate,@PathVariable Integer newSalary){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        try {
            Date fromDateAsDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            Optional<Salary> salaryToUpdate = salaryService.getSalaryByEmpIdAndFromDate(empId, fromDateAsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (salaryToUpdate.isPresent()){
                salaryToUpdate.get().setSalary(newSalary);
                salaryService.saveSalary(salaryToUpdate.get());
                ResponseEntity<String> salaryUpdatedResponse = new ResponseEntity<>(
                        objectMapper.writeValueAsString(salaryToUpdate.get().toString()),
                        httpHeaders,
                        HttpStatus.OK
                );
                return salaryUpdatedResponse;
            }else {
                ResponseEntity<String> noSalaryToUpdate = new ResponseEntity<>(
                        "{\"message\" : \"That salary doesn't exist to update\"}",
                        httpHeaders,
                        HttpStatus.NOT_FOUND);
                return noSalaryToUpdate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
