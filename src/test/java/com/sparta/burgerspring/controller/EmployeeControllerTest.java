package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeController employeeController;

//    @Test
//    @DisplayName("Check the status code of a response")
//    void checkTheStatusCodeOfAResponse() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/employees", String.class);
//        System.out.println(responseEntity.getBody());
//        assertEquals(200,responseEntity.getStatusCode().value());
//    }

    @Test
    public void CheckCorrectEmployeeFoundById(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/employee/10001", String.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(employeeRepository.findById(10001).toString(),responseEntity.getBody().toString());


    }
}
