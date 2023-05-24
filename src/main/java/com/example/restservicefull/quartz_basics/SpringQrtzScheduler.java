package com.example.restservicefull.quartz_basics;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
@EnableAutoConfiguration
public class SpringQrtzScheduler {

  @Autowired
  ApplicationContext applicationContext;

  @Bean
  public SpringBeanJobFactory springBeanJobFactory() {
    AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);
    return jobFactory;
  }

  @Bean
  public JobDetailFactoryBean jobDetail() {
    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(SampleJob.class);
    jobDetailFactory.setName("Qrtz_Job_Detail");
    jobDetailFactory.setDescription("Invoke Sample Job service...");
    jobDetailFactory.setDurability(true);
    return jobDetailFactory;
  }

  @Bean
  public SimpleTriggerFactoryBean trigger(JobDetail job) {
    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
    trigger.setJobDetail(job);

    int frequencyInSeconds = 1;

    trigger.setRepeatInterval(frequencyInSeconds * 1000);
    trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    trigger.setName("Qrtz_Trigger");
    return trigger;
  }

  @Bean
  public SchedulerFactoryBean scheduler(
    SpringBeanJobFactory springBeanJobFactory,
    Trigger trigger,
    JobDetail job
  ) {
    SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

    schedulerFactory.setJobFactory(springBeanJobFactory());
    schedulerFactory.setJobDetails(job);
    schedulerFactory.setTriggers(trigger);

    return schedulerFactory;
  }
}
