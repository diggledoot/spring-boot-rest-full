package com.example.restservicefull.quartz;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class Config {

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean fac = new SchedulerFactoryBean();
    return fac;
  }

  @Bean
  public Scheduler scheduler(SchedulerFactoryBean fac) {
    return fac.getScheduler();
  }
}
