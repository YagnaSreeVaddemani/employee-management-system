package com.springboot.employee_management_system.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.employee_management_system.entity.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Page<Employee> getEmployees(int page, int size,
                                String sortBy, String sortDir);
}