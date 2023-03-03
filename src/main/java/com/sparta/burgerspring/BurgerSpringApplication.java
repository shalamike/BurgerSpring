package com.sparta.burgerspring;


import com.sparta.burgerspring.model.repositories.DeptEmpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BurgerSpringApplication {
    private Logger logger= LoggerFactory.getLogger(BurgerSpringApplication.class);
    private final DeptEmpRepository deptEmpRepository;

    public BurgerSpringApplication(DeptEmpRepository deptEmpRepository) {
        this.deptEmpRepository = deptEmpRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BurgerSpringApplication.class, args);
    }

}
