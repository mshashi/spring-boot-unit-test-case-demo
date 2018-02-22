package com.example.demo.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
         
        Employee alex = new Employee();
        alex.setId(1l);
		alex.setName("alex");
     
        List<Employee> allEmployees = Arrays.asList(alex);
     
        when(service.getAllEmployees()).thenReturn(allEmployees);
     
        mvc.perform(get("/api/employees")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(1)))
          .andExpect(jsonPath("$[0].name", is(alex.getName())));
    }
    
    @Test
    public void givenEmployee_whenSaveEmployee_shouldSaveEmployee_thenReturnSavedEmployee()
      throws Exception {
         String employeeJSON = "{\"name\":\"bob\",\"id\":2}";
        Employee bob = new Employee();
        bob.setId(2l);
        bob.setName("bob");
     
     
        doNothing().when(service).addEmployee(any(Employee.class));
        when(service.getEmployeeByName("bob")).thenReturn(bob);
     
        mvc.perform(post("/api/addEmployee").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(employeeJSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name", is(bob.getName())));
    }
}
