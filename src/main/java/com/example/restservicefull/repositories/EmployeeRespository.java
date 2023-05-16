package com.example.restservicefull.repositories;

import com.example.restservicefull.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {}
