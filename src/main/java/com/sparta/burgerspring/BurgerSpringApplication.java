package com.sparta.burgerspring;


import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;

import com.sparta.burgerspring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactoryFriend;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class BurgerSpringApplication {
    private Logger logger= LoggerFactory.getLogger(BurgerSpringApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BurgerSpringApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner run(EmployeeRepository employeeRepository){
//
//    return args -> {
//        logger.info(employeeRepository.findByFirstNameAndAndLastName("Parto","Bamford").toString());
//    };
//    }


    }



//    @Bean
//    public CommandLineRunner run(DepartmentRepository departmentRepository, DeptEmpRepository deptEmpRepository, EmployeeRepository employeeRepository){
//    return args -> {
//        Department department=departmentRepository.findByDeptName("Development");
//    logger.info(
////            departmentRepository.findByDeptName("Development").getId()
//            new EmployeesService(
//            departmentRepository,
//            deptEmpRepository ,
//            employeeRepository).
//            getEmployeesByDateAndDepartment(
//                    LocalDate.parse("1986-07-24"),
//                    LocalDate.parse("1986-07-24"),
//                    "Development").toString()
//
////            deptEmpRepository.findByFromDateIsBeforeAndToDateAfterAndDeptNo(
////                    LocalDate.parse(
////                            "1986-07-24"),
////                    LocalDate.parse(
////                            "1986-07-24"),
////                    department).toString()
//    );
//
//    };
//
//}


