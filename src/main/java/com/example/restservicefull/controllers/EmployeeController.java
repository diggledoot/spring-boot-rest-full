package com.example.restservicefull.controllers;

import com.example.restservicefull.exceptions.EmployeeNotFoundException;
import com.example.restservicefull.models.Employee;
import com.example.restservicefull.repositories.EmployeeRespository;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeRespository repository;

  public EmployeeController(EmployeeRespository repository) {
    this.repository = repository;
  }

  @GetMapping("/employees")
  public List<Employee> all() {
    return repository.findAll();
  }

  @PostMapping("/employees")
  public Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(
    @RequestBody Employee newEmployee,
    @PathVariable Long id
  ) {
    return repository
      .findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
