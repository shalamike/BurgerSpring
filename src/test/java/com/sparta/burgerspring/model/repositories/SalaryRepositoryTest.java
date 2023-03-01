package com.sparta.burgerspring.model.repositories;

import org.junit.jupiter.api.*;
import org.mockito.internal.runners.RunnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class SalaryRepositoryTest {
@Autowired
private SalaryRepository salaryRepository;


@Test
    @DisplayName("Test")
void test (){
    SalaryRepository salaryRepository;
    List<Integer> salariesF=salaryRepository.findAll();
    System.out.println(salariesF);
}
}