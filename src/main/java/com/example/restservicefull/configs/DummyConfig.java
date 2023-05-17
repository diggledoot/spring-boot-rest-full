package com.example.restservicefull.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyConfig {

  private static final Logger log = LoggerFactory.getLogger("Hi~");

  @Bean
  CommandLineRunner dummyFunction() {
    return args -> {
      log.info("Hi from my own custom configuration!");
    };
  }
}
