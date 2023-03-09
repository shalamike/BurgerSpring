package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final ObjectMapper mapper;
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService,ObjectMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.mapper = mapper;
    }



    @GetMapping(value="/employees/date_dept")
    public ResponseEntity<String> getEmployeeByDatesAndDeptName(@RequestParam String beforeDate,
                                                        @RequestParam  String afterDate,
                                                        @RequestParam String deptName){
        LocalDate dateSince=LocalDate.parse(beforeDate);
        LocalDate dateAfter=LocalDate.parse(afterDate);
    List<Employee> employees=employeeService.getEmployeesByDateAndDepartment(dateSince, dateAfter, deptName);
        return getStringResponseEntity(employees);
//        http://localhost:8080/employees/?beforeDate=1986-07-24&afterDate=1986-07-24&deptName=Development
};
    @GetMapping(value="/employees/firstName/{firstName}")
    public ResponseEntity<String>  obtainEmployeeByFirstName(@PathVariable String firstName){
        List<Employee> employees=employeeRepository.findByFirstName(firstName);
        return getStringResponseEntity(employees);
    }

    @GetMapping(value="/employees/lastName/{lastName}")
    public ResponseEntity<String>  obtainEmployeeByLastName(@PathVariable String lastName) {
        List<Employee> employees = employeeRepository.findByLastName(lastName);
        return getStringResponseEntity(employees);
    }

    @GetMapping(value="/employees/fullName")
    public ResponseEntity<String>  obtainEmployeeByFullName(@RequestParam String firstName,
                                                            @RequestParam  String lastName) {
        List<Employee> employees = employeeRepository.findByFirstNameAndAndLastName(firstName,lastName);
        return getStringResponseEntity(employees);
    }

    private ResponseEntity<String> getStringResponseEntity(List<Employee> employees) {
        new HttpHeaders().add("content-type", "application/json");
        if (employees!=null) {
            ResponseEntity<String> response = null;
            try {
                response = new ResponseEntity<>(
                        mapper.writeValueAsString(employees),
                        new HttpHeaders(),
                        HttpStatus.OK
                );
                return response;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity<String> employeeNotFoundEntity = new ResponseEntity<>(
                "{\"message\":\"The employees doesn't exist\"}",
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
        return employeeNotFoundEntity;
    }


}
