package com.example.demo.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.EmployeeRestController;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private EmployeeService service;
 
    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
      throws Exception {
         
        Employee alex = new Employee("alex");
     
        List<Employee> allEmployees = Arrays.asList(alex);
     
        when(service.getAllEmployees()).thenReturn(allEmployees);
     
        mvc.perform(get("/api/employees")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(1)))
          .andExpect(jsonPath("$[0].name", is(alex.getName())));
    }
}
