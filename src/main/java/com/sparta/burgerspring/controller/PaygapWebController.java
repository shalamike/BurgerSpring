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
import org.springframework.web.bind.annotation.RequestParam;
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
//
    @GetMapping(value = "/paygap/department")
    public ResponseEntity<String> getPaygap(@RequestParam String department){
        String paygapInfo = salaryService.genderPaygap(department);
        return getStringResponseEntity(paygapInfo);
    }
//    @GetMapping(value = "/paygap/")
//    public ResponseEntity<String> getPaygapDefault(){
//        String paygapInfo = salaryService.genderPaygap("none");
//        return getStringResponseEntity(paygapInfo);
//    }
    @GetMapping(value = "/paygap")
    public String getDefaultAllPaygap(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        String paygapInfo = salaryService.genderPaygap("none");
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