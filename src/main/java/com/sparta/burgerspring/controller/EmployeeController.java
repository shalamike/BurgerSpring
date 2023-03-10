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
import org.springframework.web.bind.annotation.*;

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

@GetMapping(value="/employee/{id}")
public ResponseEntity<String> getEmployeeById(@PathVariable Integer id) {
    Optional<Employee> employee= employeeRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (employee.isPresent()) {
            try {
                ResponseEntity<String> response = new ResponseEntity<>(mapper.writeValueAsString(employee.get()), httpHeaders, HttpStatus.OK
                );
                return response;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity<String> employeeNotFoundResponse = new ResponseEntity<>("{\"message\":\"That employee doesn't exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
        return employeeNotFoundResponse;
}

    @PostMapping(value="/employee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
//        Optional<Employee> employeeInTable= employeeRepository.findById(employee.getId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (employeeRepository.existsById(employee.getId())) {
                ResponseEntity<String> response = new ResponseEntity<>("Employee already exist and new employee cannot be added", httpHeaders, HttpStatus.CONFLICT
                );
                return response;
        }
        ResponseEntity<String> employeeNotFoundResponse = new ResponseEntity<>("{\"message\":\"new employee added\"}", httpHeaders, HttpStatus.OK);
        employeeRepository.save(employee);
        return employeeNotFoundResponse;
    }

    @PutMapping(value ="/employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {

        Optional<Employee> updatedOptionalEmployee = employeeRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (updatedOptionalEmployee.isPresent()) {
            ResponseEntity<String> response = new ResponseEntity<>("Employee updated", httpHeaders, HttpStatus.OK
            );
            Employee updatedEmployee=updatedOptionalEmployee.get();
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setGender(employee.getGender());
            updatedEmployee.setBirthDate(employee.getBirthDate());
            updatedEmployee.setHireDate(employee.getHireDate());
            employeeRepository.save(updatedEmployee);
            return response;
        }
        ResponseEntity<String> employeeNotFoundResponse = new ResponseEntity<>("{\"message\":\"new employee added\"}", httpHeaders, HttpStatus.ACCEPTED);
        employeeRepository.save(employee);
        return employeeNotFoundResponse;
    }

    @DeleteMapping(value ="/employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id) {

        Optional<Employee> updatedOptionalEmployee = employeeRepository.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (updatedOptionalEmployee.isPresent()) {
            ResponseEntity<String> response = new ResponseEntity<>("Employee deleted", httpHeaders, HttpStatus.OK
            );
            employeeRepository.deleteById(id);
            return response;
        }
        ResponseEntity<String> employeeNotFoundResponse = new ResponseEntity<>("{\"message\":\"employee does not exist added\"}", httpHeaders, HttpStatus.NOT_FOUND);
        return employeeNotFoundResponse;
    }

    @GetMapping(value="/employees/date_dept")
    public ResponseEntity<String> getEmployeesByDatesAndDeptName(@RequestParam String beforeDate,
                                                        @RequestParam  String afterDate,
                                                        @RequestParam String deptName){
        LocalDate dateSince=LocalDate.parse(beforeDate);
        LocalDate dateAfter=LocalDate.parse(afterDate);
    List<Employee> employees=employeeService.getEmployeesByDateAndDepartment(dateSince, dateAfter, deptName);
        return getStringResponseEntity(employees);
//        http://localhost:8080/employees/date_dept?beforeDate=1986-07-24&afterDate=1986-07-24&deptName=Development
};
    @GetMapping(value="/employees/firstName/{firstName}")
    public ResponseEntity<String>  obtainEmployeesByFirstName(@PathVariable String firstName){
        List<Employee> employees=employeeRepository.findByFirstName(firstName);
        return getStringResponseEntity(employees);
    }

    @GetMapping(value="/employees/lastName/{lastName}")
    public ResponseEntity<String>  obtainEmployeesByLastName(@PathVariable String lastName) {
        List<Employee> employees = employeeRepository.findByLastName(lastName);
        return getStringResponseEntity(employees);
    }

    @GetMapping(value="/employees/fullName/{firstName}/{lastName}")
    public ResponseEntity<String>  obtainEmployeesByFullName(@PathVariable String firstName,
                                                            @PathVariable  String lastName) {
        List<Employee> employees = employeeRepository.findByFirstNameAndAndLastName(firstName,lastName);
        return getStringResponseEntity(employees);
    }
    private ResponseEntity<String> getStringResponseEntity(List<Employee> employees) {
        new HttpHeaders().add("content-type", "application/json");
        if (employees.size()!=0) {
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
