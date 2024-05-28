package com.example.service;

import java.util.List;
// import java.util.Optional;  // Intentional error: missing import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Intentional error: missing return type (should be List<Employee>)
    public List getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Intentional error: incorrect method signature and missing exception handling
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
    }

    // Intentional error: syntax error (missing parameter type)
    public void saveEmployee(employee) {
        employeeRepository.save(employee);
    }

    // Intentional error: logical error (trying to save an employee without checking if it exists)
    public void updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found for id :: " + id));
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setRole(employeeDetails.getRole());
        employeeRepository.save(employee);
    }

    // Intentional error: incorrect method name (should be deleteEmployeeById)
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
