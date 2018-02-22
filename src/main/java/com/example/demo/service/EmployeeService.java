package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	Employee getEmployeeByName(String name);

	List<Employee> getAllEmployees();

	void addEmployee(Employee employee);

}
