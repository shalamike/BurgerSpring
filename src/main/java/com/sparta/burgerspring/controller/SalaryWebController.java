package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.model.entities.Salary;
import com.sparta.burgerspring.model.entities.SalaryId;
import com.sparta.burgerspring.model.repositories.SalaryRepository;
import com.sparta.burgerspring.service.EmployeeService;
import com.sparta.burgerspring.service.SalaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("/salary")
    public String salaryHome(){
        return "salary/salary-home";
    }

    @GetMapping("/salaries")
    public String getSalaries(){
        return "salary/salary-search-form";
    }

    @GetMapping("/salaries/search")
    public String getSalaryDetails(Model model, @RequestParam Integer empNo) {
        model.addAttribute("salaries", salaryRepository.getDetailsByEmpNo(empNo));
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
    public String createSalary(@ModelAttribute("salaryToCreate")Salary newSalary, @ModelAttribute("salaryIdToCreate")SalaryId newSalaryId){
        newSalary.setId(newSalaryId);
        if(newSalary.getToDate() == null){
            newSalary.setToDate(LocalDate.of(9999, 01, 01));
        }
        System.out.println(newSalaryId);
        salaryRepository.saveAndFlush(newSalary);
        return "salary/add-salary-success";
    }

    @GetMapping("/salary/edit/{id}/{fromDate}")
    public String getUpdatedSalaryDetails(@PathVariable Integer id,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, Model model) {
        SalaryId salaryId = new SalaryId();
        salaryId.setEmpNo(id);
        salaryId.setFromDate(fromDate);
        Salary salary = salaryRepository.findById(salaryId).orElse(null);
        model.addAttribute("salaryToEdit", salary);
        return "salary/salary-update-form";
    }

    @PostMapping("/updatesalary")
    public String updateSalary(@ModelAttribute("updatedSalary")Salary updatedSalary, @ModelAttribute("salaryIdToCreate")SalaryId newSalaryId){
        if(updatedSalary.getToDate() == null){
            updatedSalary.setToDate(LocalDate.of(9999, 01, 01));
        }
        salaryRepository.saveAndFlush(updatedSalary);
        return "salary/salary-update-success";
    }

    @GetMapping("/salary/edit")
    public String getSalaryToUpdate(){
        return "salary/salary-to-update-form";
    }

    @PostMapping("/findsalary")
    public String findSalaryToUpdate(@ModelAttribute("salaryToUpdate") SalaryId foundSalary) {
        return "redirect:/salary/edit/" + foundSalary.getEmpNo() + "/" + foundSalary.getFromDate();
    }

    @GetMapping("salary/delete")
    public String getSalaryToDelete(){
        return "salary/salary-to-delete-form";
    }

    @PostMapping("/deletesalary")
    public String findSalaryToDelete(@ModelAttribute("salaryToDelete") SalaryId foundSalary) {
        return "redirect:/salary/delete/" + foundSalary.getEmpNo() + "/" + foundSalary.getFromDate();
    }

    @GetMapping("/salary/delete/{id}/{fromDate}")
    public String deleteSalary(@PathVariable Integer id,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate){
        SalaryId salaryId = new SalaryId();
        salaryId.setEmpNo(id);
        salaryId.setFromDate(fromDate);
        salaryRepository.deleteById(salaryId);
        return "salary/salary-delete-success";
    }

//    @GetMapping("/salaries")
//    public String getAllSalaries(Model model) {
//        model.addAttribute("salaries", salaryRepository.findAll());
//        return "salary/salaries";
//    }

}