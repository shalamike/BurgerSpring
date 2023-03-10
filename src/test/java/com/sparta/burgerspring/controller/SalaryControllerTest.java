package com.sparta.burgerspring.controller;


import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SalaryControllerTest {

    @Autowired
    MockMvc mvc;


    @Test
    void contextLoads(){

    }

    @Test
    @DisplayName("Check that when a user searches for all an employee's highest salary, they get a 200 response")
    void checkThatMaxEmployeesSalaryIsReturnedWitValidEmpID(){
        try {
            mvc.perform(MockMvcRequestBuilders.get("/salary/100037")) //this line creates the post request
                    .andDo(MockMvcResultHandlers.print()) //this line just prints the results of the post request
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()); //this line is the assertion checking that thte satus code is = to any 200 success code
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Check that when a user searches for all an employee's highest salary, they get a 200 response")
    void CheckThatAllEmployeesReturn200Response(){
        try {
            mvc.perform(MockMvcRequestBuilders.get("/salaries/100000")) //this line creates the post request
                    .andDo(MockMvcResultHandlers.print()) //this line just prints the results of the post request
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()); //this line is the assertion checking that thte satus code is = to any 200 success code
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("Check that when a user searches for all an employee's highest salary, they get a 200 response")
    void checkThatEmployeeId100027WillReturnVallidResponse(){
        try {
            MvcResult resultSet = mvc.perform(MockMvcRequestBuilders.get("/salary/100027")) //this line creates the post request
                    .andDo(MockMvcResultHandlers.print()) //this line just prints the results of the post request
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()) //this line is the assertion checking that thte satus code is = to any 200 success code
                    .andReturn(); //and return returns the results as a MvcResults variable

            String content = resultSet.getResponse().getContentAsString();

            Assertions.assertEquals("{100027, Ayakannu Coullard : 69447}", content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"10637 Heejo Frolund : 117547", "10292 Yucel Ghelli : 106632", "10107 Dung Baca : 101676"})
    @DisplayName("Check that when a user searches for all an employee's highest salary, they get a 200 response")
    void CheckThatCertainEmployeesArePresentWhenSalariesSearchedISAbove100000(String emp){
        try {
            MvcResult resultSet = mvc.perform(MockMvcRequestBuilders.get("/salaries/100000")) //this line creates the post request
                    .andDo(MockMvcResultHandlers.print()) //this line just prints the results of the post request
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn(); //this line is the assertion checking that thte satus code is = to any 200 success code

            String content = resultSet.getResponse().getContentAsString();
            boolean contentContainsEmployee = content.contains(emp);
            Assertions.assertTrue(contentContainsEmployee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Test
    @DisplayName("Check that a salary has been updated")
    void checkThatSalaryHasBeenUpdated(){
        try {
            MvcResult resultSet = mvc.perform(MockMvcRequestBuilders.put("/salary/10001/1986-06-26/60117")) //this line creates the post request
                    .andDo(MockMvcResultHandlers.print()) //this line just prints the results of the post request
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()) //this line is the assertion checking that thte satus code is = to any 200 success code
                    .andReturn(); //and return returns the results as a MvcResults variable

            String content = resultSet.getResponse().getContentAsString();

            Assertions.assertEquals("Salary{id=SalaryId{empNo=10001, fromDate=1986-06-26}, empNo=Employee{id=10001, birthDate=1953-09-02, firstName='Georgi', lastName='Facello', gender='M', hireDate=1986-06-26}, salary=60117, toDate=1987-06-26}", content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
