package com.sparta.burgerspring.controller;

import com.sparta.burgerspring.model.entities.Department;
import com.sparta.burgerspring.model.repositories.DepartmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class PaygapControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    DepartmentRepository departmentRepository;
    @Test
    @DisplayName("Check when they search for paygap as default it returns OK")
    void checkPaygapDefaultReturnsOK(){
        try {
            mvc.perform(MockMvcRequestBuilders.get("/paygap"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Check when they search for paygap without parameters it returns OK")
    void checkPaygapNoParamsReturnsOK(){
        try {
            mvc.perform(MockMvcRequestBuilders.get("/paygap/"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Check when they search for paygap with parameters it returns OK")
    void checkPaygapAllReturnsOK(){
        String paygapURL;
        List<String> departmentNames = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for(Department depts: departments){
            departmentNames.add(depts.getDeptName().toLowerCase(Locale.ROOT));
        }
        for(String deptsParam:departmentNames) {
            paygapURL = "/paygap/" + deptsParam;
            try {
                mvc.perform(MockMvcRequestBuilders.get(paygapURL))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    @DisplayName("Check when they search for a paygap in all departments, it returns a response string containing the department and the word paygap")
    void checkPaygapParamAllReturnsStringContainingAllDepartmentsAndPaygap(){
        String paygapURL;
        List<String> departmentNames = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for(Department depts: departments){
            departmentNames.add(depts.getDeptName().toLowerCase(Locale.ROOT));
        }
        for(String deptsParam:departmentNames) {
            paygapURL = "/paygap/" + deptsParam;
            try {
                MvcResult response = mvc.perform(MockMvcRequestBuilders.get(paygapURL))
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();
                String content = response.getResponse().getContentAsString();
                assertTrue(content.contains(deptsParam));
                assertTrue(content.contains("paygap"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}