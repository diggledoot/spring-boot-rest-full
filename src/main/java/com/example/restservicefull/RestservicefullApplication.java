package com.example.restservicefull;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestservicefullApplication {

  public static void main(String[] args) throws SchedulerException {
    SpringApplication.run(RestservicefullApplication.class, args);
  }
}
