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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
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

    @GetMapping(value = "get/departments")
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    @GetMapping(value = "get/departments")
    public String getAllDepartments(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "getalldepartments";
    }

    @GetMapping(value = "get/departments/deptListByName/{firstName}/{lastName}")
    public String getDepartments(@PathVariable String firstName, @PathVariable String lastName){
        return departmentService.getListOfDeptNamesByEmp(firstName, lastName);
    }

    @PostMapping(value = "post/department/createNewDept")
    public String setDepartment(@RequestBody Department department){
        departmentRepository.save(department);

        return "Department: " + department.getDeptName() + " added";
    }

    @PutMapping(value = "put/departments/{id}")
    public ResponseEntity<String> updateDepartmentName(@PathVariable String id, @RequestBody String newDeptName){
        Department foundDepartment = departmentRepository.findDepartmentById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(foundDepartment != null){
            ResponseEntity<String> response = new ResponseEntity<>("{\"message\";\"The department " + foundDepartment.getDeptName() + " has been updated\"}", httpHeaders, HttpStatus.OK);
            foundDepartment.setDeptName(newDeptName);
            departmentRepository.save(foundDepartment);
            return response;
        }
        else{
            return new ResponseEntity<>("{\"message\";\"The department " + id + " doesn't exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "delete/departments/{id}")
    public ResponseEntity<String> deleteDeptById(@PathVariable String id){
        Department foundDepartment = departmentRepository.findDepartmentById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if(foundDepartment != null){
            ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"message\";\"That department has been deleted\"}", httpHeaders, HttpStatus.OK);
            departmentRepository.deleteById(id);
            return responseEntity;
        }
        else {
            return new ResponseEntity<>("{\"message\";\"That department doesn't exist\"}", httpHeaders, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "get/departments/avgSalByDeptNameAndDate/{deptName}/{date}")
    public String getAvgSalByDeptNameAndDate(@PathVariable String deptName, @PathVariable LocalDate date){
        return departmentService.getAvgSalByDeptNameAndDate(deptName, date);
    }
}
