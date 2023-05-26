package com.example.restservicefull.quartz;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class Config {

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    return schedulerFactoryBean;
  }

  @Bean
  public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
    return schedulerFactoryBean.getScheduler();
  }
}
