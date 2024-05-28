package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Hardcoded credentials - BAD PRACTICE
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employees";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // SQL Injection vulnerability
    public void updateEmployeeEmail(Long id, String email) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE employees SET email = '" + email + "' WHERE id = " + id;
            stmt.executeUpdate(sql);
            conn.close();
        } catch (Exception e) {
            // Improper exception handling - should log or handle appropriately
            e.printStackTrace();
        }
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
