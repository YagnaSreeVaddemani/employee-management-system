package com.springboot.employee_management_system.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.employee_management_system.entity.Employee;
import com.springboot.employee_management_system.exception.ResourceNotFoundException;
import com.springboot.employee_management_system.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE EMPLOYEE
    @Override
    public Employee createEmployee(Employee employee) {

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException(
                    "An employee with this email already exists");
        }

        // Ensure a new employee is created
        employee.setId(null);

        return employeeRepository.save(employee);
    }

    // GET ALL EMPLOYEES
    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    // GET EMPLOYEE BY ID
    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with ID: " + id));
    }

    // UPDATE EMPLOYEE
    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = getEmployeeById(id);

        boolean emailExists =
                employeeRepository.existsByEmail(employee.getEmail());

        boolean emailChanged =
                existing.getEmail() == null
                || employee.getEmail() == null
                || !existing.getEmail().equalsIgnoreCase(
                        employee.getEmail());

        if (emailExists && emailChanged) {
            throw new IllegalArgumentException(
                    "An employee with this email already exists");
        }

        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return employeeRepository.save(existing);
    }

    // DELETE EMPLOYEE
    @Override
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }

    // PAGINATION AND SORTING
    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getEmployees(
            int page, int size, String sortBy, String sortDir) {

        // Validate page and size
        if (page < 0 || size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page must be non-negative and size must be between 1 and 100");
        }

        // Validate sorting field
        List<String> allowedFields = Arrays.asList(
                "id", "firstName", "lastName",
                "email", "department", "salary");

        if (sortBy == null || !allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field");
        }

        // Validate sorting direction
        if (sortDir == null ||
                (!sortDir.equalsIgnoreCase("asc")
                && !sortDir.equalsIgnoreCase("desc"))) {

            throw new IllegalArgumentException(
                    "Sort direction must be 'asc' or 'desc'");
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable);
    }
}