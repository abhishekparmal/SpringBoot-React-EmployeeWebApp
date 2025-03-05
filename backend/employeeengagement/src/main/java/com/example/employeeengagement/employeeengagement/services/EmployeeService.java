package com.example.employeeengagement.employeeengagement.services;

import java.util.List;

import com.example.employeeengagement.employeeengagement.model.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
