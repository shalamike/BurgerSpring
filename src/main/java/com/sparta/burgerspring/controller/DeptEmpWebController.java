package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.BurgerSpringApplication;
import com.sparta.burgerspring.model.entities.*;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeptEmpWebController {
    private DeptEmpRepository deptEmpRepository;
    private DepartmentRepository departmentRepository;


    public DeptEmpWebController(DeptEmpRepository deptEmpRepository, DepartmentRepository departmentRepository) {
        this.deptEmpRepository = deptEmpRepository;
        this.departmentRepository = departmentRepository;
    }

    //find all
    @GetMapping("/deptEmps")
    public String getAllDeptEmps(Model model){
        List<DeptEmp> deptEmpList=deptEmpRepository.findAll().subList(0,50);
        model.addAttribute("deptEmps",deptEmpList);
        return "deptEmp/deptEmps";
    }

    //create
    @GetMapping("/deptEmp/create")
    public String createDeptEmp() {
        return "deptEmp/deptEmp-add-form";
    }
    @PostMapping("/createDeptEmp")
    public String createDeptEmp(@ModelAttribute("DeptEmpToCreate")DeptEmp addedDeptEmp, @ModelAttribute("DeptEmpIdToCreate")DeptEmpId newDeptEmpId) {
        BurgerSpringApplication.logger.info(addedDeptEmp.toString());
        BurgerSpringApplication.logger.info(newDeptEmpId.toString());
        addedDeptEmp.setId(newDeptEmpId);
        deptEmpRepository.save(addedDeptEmp);
        return "fragments/create-success";
    }

    //Read
    @GetMapping("/deptEmp/find")
    public String findDeptEmp() {
        return "deptEmp/deptEmp-find-form";
    }
    @PostMapping("/findDeptEmpById")
    public String findDeptEmp(@ModelAttribute("DeptEmpIdToCreate")DeptEmpId newDeptEmpId,
                              String deptNo,Integer empNo,Model model
    ) {
        BurgerSpringApplication.logger.info(deptNo);
        BurgerSpringApplication.logger.info(String.valueOf(empNo));
        BurgerSpringApplication.logger.info(newDeptEmpId.toString());
       Department department= departmentRepository.findById(deptNo).orElse(null);


        model.addAttribute("empDept",deptEmpRepository.findDeptEmpByDeptNoAndId(department,newDeptEmpId));
        return "deptEmp/deptEmp";
    }

    //update
//    @GetMapping("/employee/edit/{id}")
//    public String getEmployeeToEdit(@PathVariable Integer id, Model model) {
//        Employee employee = deptEmpRepository.findById(id).orElse(null);
//        model.addAttribute("employeeToEdit", employee);
//        return "deptEmp-edit-form";
//    }
//    @PostMapping("/updateEmployee")
//    public String updateEmployee(@ModelAttribute("employeeToEdit")Employee editedEmployee) {
//        deptEmpRepository.saveAndFlush(editedEmployee);
//        return "fragments/edit-success";
//    }
//
//    //delete
//    @GetMapping("/employee/delete/{id}")
//    public String deleteEmployee(@PathVariable Integer id) {
//        deptEmpRepository.deleteById(id);
//        return "fragments/delete-success";
//    }






}
