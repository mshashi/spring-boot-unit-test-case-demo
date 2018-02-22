package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
 
    @Autowired
    private EmployeeService employeeService;
 
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
    	System.out.println(employee);
    	employeeService.addEmployee(employee);
        return employeeService.getEmployeeByName(employee.getName());
    }
}
