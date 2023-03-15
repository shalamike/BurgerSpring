package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.repositories.SalaryRepository;
import com.sparta.burgerspring.service.EmployeeService;
import com.sparta.burgerspring.service.SalaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SalaryWebController {

    private final SalaryService salaryService;
    private final EmployeeService employeeService;
    private final SalaryRepository salaryRepository;

    public SalaryWebController(SalaryService salaryService, EmployeeService employeeService, SalaryRepository salaryRepository) {
        this.salaryService = salaryService;
        this.employeeService = employeeService;
        this.salaryRepository = salaryRepository;
    }


    @GetMapping(value = "/salary/{id}")
    public String getSalaryById(Model model, @PathVariable Integer id){
        model.addAttribute("salary", salaryService.getEmployeeHighestSalaryByEmployeeId(id));
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        return "salary/salary";
    }

    @GetMapping(value = "/salaries/{salary}")
    public String getEmployeesEarningAboveSalary(Model model, @PathVariable Integer salary){
        model.addAttribute("employees", salaryService.getEmployeeEarningAboveGivenSalary(salary));
        return "salary/salariesemployees";
    }

    @GetMapping("/salary/create")
    public String getSalaryToCreate() {
        return "salary/salary-create-form";
    }

    @PostMapping("/createsalary")
    public String createSalary(@ModelAttribute("salaryToCreate")Salary newSalary){
        salaryRepository.saveAndFlush(newSalary);
        return "salary/add-salary-success";
    }
}