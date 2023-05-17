package com.example.restservicefull.configs;

import com.example.restservicefull.models.Employee;
import com.example.restservicefull.repositories.EmployeeRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRespository repository) {
    return args -> {
      log.info(
        "Preloading " +
        repository.save(new Employee("Bilbo", "Baggins", "thief"))
      );
      log.info(
        "Preloading " +
        repository.save(new Employee("Frodo", "Baggins", "thief"))
      );
    };
  }
}
