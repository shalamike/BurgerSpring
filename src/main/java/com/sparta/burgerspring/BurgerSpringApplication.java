package com.sparta.burgerspring;

import com.sparta.burgerspring.model.repositories.SalaryRepository;
import com.sparta.burgerspring.service.SalaryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BurgerSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerSpringApplication.class, args);
    }
}
