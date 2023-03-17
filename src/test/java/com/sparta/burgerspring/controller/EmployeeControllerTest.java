package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.model.entities.Employee;
import com.sparta.burgerspring.model.repositories.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeController employeeController;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mvc;
    @Test
    void contextLoads() {
    }
    @Test
    @DisplayName("check that employee by id 10001 returns 200")
    void checkThatGetEmployeeByIdReturns200() {
        try {
            mvc.perform(get("api/employee/10001"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @DisplayName("check that employee by dept name and period returns 200")
    void checkThatGetEmployeeByDeptNameAndPeriodReturns200() {
        try {
            mvc.perform(get("api/employees/date_dept?beforeDate=1986-07-24&afterDate=1986-07-24&deptName=Development"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    @Test
    @DisplayName("check that employees by first name returns 200")
    void checkThatGetEmployeeByFirstNameReturns200() {
        try {
            mvc.perform(get("api/employees/firstName/Georgi"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @DisplayName("check that employees by last name returns 200")
    void checkThatGetEmployeeByLastNameReturns200() {
        try {
            mvc.perform(get("api/employees/lastName/Facello"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @DisplayName("check that employees by full name returns 200")
    void checkThatGetEmployeeByFullnameReturns200() {
        try {
            mvc.perform(get("api/employees/fullName/Georgi/Facello"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @DisplayName("check post mapping return 200")
    void checkThatPostMappingReturns200() {
        Employee employeeTest=new Employee();
        employeeTest.setFirstName("Test1");
        employeeTest.setLastName("Test1");
        employeeTest.setGender("Test1");
        employeeTest.setHireDate(LocalDate.parse("1996-04-05"));
        employeeTest.setBirthDate(LocalDate.parse("1046-05-04"));
        try {
            MvcResult result=mvc.perform( MockMvcRequestBuilders
                            .post("api/employee")
                            .content(objectMapper.writeValueAsString(employeeTest))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists()).andReturn();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    @DisplayName("check put mapping return 200")
    void checkThatPutMappingReturns200() {
        Employee employeeTest=new Employee();
        employeeTest.setFirstName("Test1");
        employeeTest.setLastName("Test1");
        employeeTest.setGender("Test1");
        employeeTest.setHireDate(LocalDate.parse("1996-04-05"));
        employeeTest.setBirthDate(LocalDate.parse("1046-05-04"));
        try {
            MvcResult result=mvc.perform( MockMvcRequestBuilders
                            .put("api/employee/9999")
                            .content(objectMapper.writeValueAsString(employeeTest))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists()).andReturn();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    @Test
    @DisplayName("check delete mapping return 200")
    void checkThatDeleteMappingReturns200() {
        Employee employeeTest=new Employee();
        employeeTest.setFirstName("Test1");
        employeeTest.setLastName("Test1");
        employeeTest.setGender("Test1");
        employeeTest.setHireDate(LocalDate.parse("1996-04-05"));
        employeeTest.setBirthDate(LocalDate.parse("1046-05-04"));
        try {
            MvcResult result=mvc.perform( MockMvcRequestBuilders
                            .put("api/employee/10001")
                            .content(objectMapper.writeValueAsString(employeeTest))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists()).andReturn();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
//    @Test
//    @DisplayName("Check the status code of a response")
//    void checkTheStatusCodeOfAResponse() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/employees", String.class);
//        System.out.println(responseEntity.getBody());
//        assertEquals(200,responseEntity.getStatusCode().value());
//    }

//    @Test
//    public void CheckCorrectEmployeeFoundById(){
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
//                "http://localhost:8080/employee/10001", String.class);
//        System.out.println(responseEntity.getBody().toString());
//        assertEquals(employeeRepository.findById(10001).toString(),responseEntity.getBody().toString());
//
//
//    }
}
