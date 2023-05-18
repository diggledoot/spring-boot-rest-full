package com.example.restservicefull.configs;

import com.example.restservicefull.models.Employee;
import com.example.restservicefull.models.Order;
import com.example.restservicefull.models.Status;
import com.example.restservicefull.repositories.EmployeeRespository;
import com.example.restservicefull.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
    EmployeeRespository employeeRepository,
    OrderRepository orderRepository
  ) {
    return args -> {
      employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
      employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

      employeeRepository
        .findAll()
        .forEach(employee -> log.info("Preloaded " + employee));

      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository
        .findAll()
        .forEach(order -> {
          log.info("Preloaded " + order);
        });
    };
  }
}
