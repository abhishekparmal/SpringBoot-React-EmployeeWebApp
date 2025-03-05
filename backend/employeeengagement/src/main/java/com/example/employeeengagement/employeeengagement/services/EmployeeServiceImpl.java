package com.example.employeeengagement.employeeengagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeengagement.employeeengagement.entity.EmployeeEntity;
import com.example.employeeengagement.employeeengagement.model.Employee;
import com.example.employeeengagement.employeeengagement.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(employee.getName());
        entity.setDepartment(employee.getDepartment());
        entity.setSalary(employee.getSalary());

        EmployeeEntity savedEntity = employeeRepository.save(entity);
        return new Employee(savedEntity.getId(), savedEntity.getName(), savedEntity.getDepartment(), savedEntity.getSalary());
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        return new Employee(entity.getId(), entity.getName(), entity.getDepartment(), entity.getSalary());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(emp -> new Employee(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity entity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        entity.setName(employee.getName());
        entity.setDepartment(employee.getDepartment());
        entity.setSalary(employee.getSalary());

        EmployeeEntity updatedEntity = employeeRepository.save(entity);
        return new Employee(updatedEntity.getId(), updatedEntity.getName(), updatedEntity.getDepartment(), updatedEntity.getSalary());
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(entity);
    }
}
