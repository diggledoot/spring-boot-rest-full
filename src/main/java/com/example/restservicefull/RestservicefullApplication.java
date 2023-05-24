package com.example.restservicefull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestservicefullApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestservicefullApplication.class, args);
  }
}
