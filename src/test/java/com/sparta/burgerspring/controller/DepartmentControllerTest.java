package com.sparta.burgerspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.burgerspring.model.entities.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void contextLoads() {

    }

    @Test
    @DisplayName("When a user searches using /departments, the status code is 200")
    void givenUserSearchesDepartments_Returns200() {
        try {
            mvc.perform(get("/departments"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("When a user searches using /department, check the status code is 405")
    void givenUserSearchesDepartment_Returns400() {
        try {
            mvc.perform(get("/department"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    @DisplayName("When a user posts a new department name, check the status code is 200")
//    void givenUserPostsDepartmentName_Returns200() {
//        try {
//            mvc.perform(post("/department")
//                    .content(asJsonString((new Department("d007", "Sales"))))
//                    .contentType()
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static String asJsonString(final Department department) {
//        try {
//            return new ObjectMapper().writeValueAsString(department);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



}
