package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DepartmentController {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    private DepartmentService departmentService;

    private ObjectMapper mapper;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, DepartmentService departmentService, ObjectMapper mapper){
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/departments")
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    @GetMapping(value = "/departments/deptListByName")
    public String getDepartments(@RequestParam String firstName, @RequestParam String lastName){
        return departmentService.getListOfDeptNamesByEmp(firstName, lastName);
    }

    @PostMapping(value = "/department")
    public String setDepartment(@RequestBody Department department){
        departmentRepository.save(department);

        return "Department: " + department.getDeptName() + " added";
    }

    @PutMapping(value = "/department/{deptName}")
    public ResponseEntity<String> updateDepartmentName(@PathVariable String deptName, @RequestBody Department department){
        Department foundDepartment = departmentRepository.findByDeptName(deptName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(foundDepartment != null){
            ResponseEntity<String> response = new ResponseEntity<>("{\"message\";\"The department " + department.getDeptName() + " has been updated\"}", httpHeaders, HttpStatus.OK);
            department.setDeptName(deptName);
            departmentRepository.save(department);
            return response;
        }
        else{
            ResponseEntity<String> noAuthorToUpdate = new ResponseEntity<>("{\"message\";\"The department " + department.getDeptName() + " doesn't exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
            return noAuthorToUpdate;
        }
    }






}
