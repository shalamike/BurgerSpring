package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.service.SalaryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PaygapWebController {

    private SalaryService salaryService;
    private final DepartmentRepository departmentRepository;

    public PaygapWebController(SalaryService salaryService,
                               DepartmentRepository departmentRepository) {
        this.salaryService = salaryService;
        this.departmentRepository = departmentRepository;
    }
    @GetMapping(value = "/paygap/{department}")
    public String getPaygap(@PathVariable String department, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("output", salaryService.genderPaygap(department));
        return "/paygap/paygap.html";
    }
    @GetMapping(value = "/paygap")
    public String getDefaultAllPaygap(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "/paygap/paygap.html";
    }

    private ResponseEntity<String> getStringResponseEntity(String paygapInfo) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type","application/json");
        ResponseEntity<String> response = null;
        if(paygapInfo!=null){
            response = new ResponseEntity<>("{\"message\": \""+paygapInfo+"\" }",
                    httpHeaders, HttpStatus.OK);
        }
        return response;
    }
}