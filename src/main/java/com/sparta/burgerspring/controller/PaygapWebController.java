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
        String str = salaryService.genderPaygap(department);
        model.addAttribute("output", str);
        str = str.replaceAll("[^\\d.]", "");
        if(str.isEmpty()){
            str = "0";
        }
        Double percentage = Double.parseDouble(str);
        if(str.equals("0")){
            str = "Check one or all of our departments to see the paygap between genders";
        }
        else if(percentage>2||percentage<-2){
            str = "Oh no, there seems to be a substantial paygap";
        } else if (percentage<2&&percentage>-2) {
            str = "Congrats, there is no substantial paygap";

        }
        model.addAttribute("percentage", str);
        return "/paygap/paygap.html";
    }
    @GetMapping(value = "/paygap")
    public String getDefaultAllPaygap(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("percentage", "Check one or all of our departments to see the paygap between genders");
        return "/paygap/paygap.html";
    }

}