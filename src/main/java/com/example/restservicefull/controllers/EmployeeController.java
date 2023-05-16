package com.example.restservicefull.controllers;

import com.example.restservicefull.exceptions.EmployeeNotFoundException;
import com.example.restservicefull.models.Employee;
import com.example.restservicefull.repositories.EmployeeRespository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeRespository repository;

  public EmployeeController(EmployeeRespository repository) {
    this.repository = repository;
  }

  // @GetMapping("/employees")
  // public List<Employee> all() {
  //   return repository.findAll();
  // }
  @GetMapping("/employees")
  public CollectionModel<EntityModel<Employee>> all() {
    List<EntityModel<Employee>> employees = repository
      .findAll()
      .stream()
      .map(employee ->
        EntityModel.of(
          employee,
          WebMvcLinkBuilder
            .linkTo(
              WebMvcLinkBuilder
                .methodOn(EmployeeController.class)
                .one(employee.getId())
            )
            .withSelfRel(),
          WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all())
            .withRel("employees")
        )
      )
      .collect(Collectors.toList());

    return CollectionModel.of(
      employees,
      WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all())
        .withSelfRel()
    );
  }

  @PostMapping("/employees")
  public Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {
    Employee employee = repository
      .findById(id) //
      .orElseThrow(() -> new EmployeeNotFoundException(id));
    return EntityModel.of(
      employee,
      WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).one(id))
        .withSelfRel(),
      WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all())
        .withRel("employees")
    );
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
