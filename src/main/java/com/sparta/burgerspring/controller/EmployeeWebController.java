package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.BurgerSpringApplication;
import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.entities.DeptEmp;
import com.sparta.burgerspring.model.entities.DeptEmpId;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import com.sparta.burgerspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EmployeeWebController {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    @Autowired
    public EmployeeWebController(EmployeeRepository employeeRepository,EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService=employeeService;
    }
    //find all
    @GetMapping("/employees")
    public String getAllEmployees(Model model){
        List<Employee> employeeList=employeeRepository.findAll().subList(0,10);
        model.addAttribute("employees",employeeList);
        return "employee/employees";
    }


    //create
    @GetMapping("/employee/create")
    public String createEmployee() {
        return "employee/employee-add-form";
    }
    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute("employeeToCreate")Employee addedEmployee) {
        employeeRepository.save(addedEmployee);
        return "fragments/create-success";
    }






//Read
    @GetMapping("/employee/find")
    public String findEmployee() {
        return "employee/employee-find-form";
    }
@PostMapping("/findEmployeeById")
public String findEmployee(@ModelAttribute("employeeToFind")Employee foundEmployee,Model model
) {
    BurgerSpringApplication.logger.info(foundEmployee.toString());
    Integer id=foundEmployee.getId();
    List<Employee> employees;
    if(id!=null){
        Employee employee=employeeRepository.findById(id).orElse(null);
        employees=new ArrayList<Employee>();
        if (employee != null) {
            employees.add(employee);
            model.addAttribute("employees",employees);
        }
    } else {
        model.addAttribute("employees",null);

    }
return "employee/employee";
}
    @PostMapping("/findEmployeeByName")
    public String findEmployeeByName(@ModelAttribute("employeeToFind")Employee foundEmployee,Model model
    ) {
        BurgerSpringApplication.logger.info(foundEmployee.toString());
        String firstName=foundEmployee.getFirstName();
        String lastName=foundEmployee.getLastName();
        BurgerSpringApplication.logger.info(firstName);
        BurgerSpringApplication.logger.info(lastName);
        List<Employee> employees;
         if(!firstName.equals("") && !lastName.equals("")) {
            employees=employeeRepository.findByFirstNameAndAndLastName(firstName,lastName);
        } else if(!firstName.equals("")){
            employees=employeeRepository.findByFirstName(firstName);
            BurgerSpringApplication.logger.info(employees.toString());
        } else if(!lastName.equals("")){
            employees=employeeRepository.findByFirstName(lastName);
        }else {
            employees=new ArrayList<Employee>();
        }

        if(employees.size()==0){
            model.addAttribute("employees",null);
        } else {
            model.addAttribute("employees",employees);
        }
        return "employee/employee";
    }
    @PostMapping("/findEmployeeByDepartAndDate")
    public String findEmployeeByDeptNameAndDate(LocalDate fromDate, LocalDate toDate, String deptName,
                               Model model
    ) {
        List<Employee> employees=employeeService.getEmployeesByDateAndDepartment(fromDate,toDate,deptName);
        if(employees.size()==0){
            model.addAttribute("employees",null);
        } else {
            model.addAttribute("employees",employees);
        }
        return "employee/employee";
    }
    //update
    @GetMapping("/employee/edit/{id}")
    public String getEmployeeToEdit(@PathVariable Integer id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        model.addAttribute("employeeToEdit", employee);
        return "employee/employee-edit-form";
    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employeeToEdit")Employee editedEmployee) {
        employeeRepository.saveAndFlush(editedEmployee);
        return "fragments/edit-success";
    }

    //delete
    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
        return "fragments/delete-success";
    }






}
