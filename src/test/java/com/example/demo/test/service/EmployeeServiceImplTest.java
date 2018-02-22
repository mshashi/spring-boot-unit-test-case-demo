package com.example.demo.test.service;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
 
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }
 
    @Autowired
    private EmployeeService employeeService;
 
    @MockBean
    private EmployeeRepository employeeRepository;
    
    @Before
    public void setUp() {
        Employee alex = new Employee();
        alex.setName("alex");
        alex.setId(1l);
     
        Mockito.when(employeeRepository.findByName(alex.getName()))
          .thenReturn(alex);
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);
      
         assertEquals(found.getName(),"alex");
         assertEquals(found.getId().longValue(),1);
     }
 
}