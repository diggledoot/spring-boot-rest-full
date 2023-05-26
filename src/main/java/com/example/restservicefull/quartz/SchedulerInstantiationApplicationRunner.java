package com.example.restservicefull.quartz;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SchedulerInstantiationApplicationRunner
  implements ApplicationRunner {

  private static final Logger log = LoggerFactory.getLogger(
    SchedulerInstantiationApplicationRunner.class
  );

  @Autowired
  SchedulerService service;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    //Initialize Scheduler instance
    log.info("Running the application runner for scheduler instance");
    service.startAllJobs();
    //Logic to instantiate initial jobs
  }
}
