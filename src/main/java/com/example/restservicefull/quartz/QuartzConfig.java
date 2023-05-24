package com.example.restservicefull.quartz;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {

  final ApplicationContext applicationContext;

  public QuartzConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  public SpringBeanJobFactory createSpringBeanJobFactory() {
    return new SpringBeanJobFactory() {
      @Override
      protected Object createJobInstance(TriggerFiredBundle bundle)
        throws Exception {
        final Object job = super.createJobInstance(bundle);

        applicationContext.getAutowireCapableBeanFactory().autowireBean(job);
        return job;
      }
    };
  }

  @Bean
  public JobDetailFactoryBean jobDetailFactory() {
    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(QuartzJob.class);
    return jobDetailFactory;
  }

  @Bean
  public SimpleTriggerFactoryBean triggerFactory(JobDetail jobDetail) {
    SimpleTriggerFactoryBean triggerFactory = new SimpleTriggerFactoryBean();
    triggerFactory.setJobDetail(jobDetail);
    triggerFactory.setStartDelay(0);
    triggerFactory.setRepeatInterval(2000);

    return triggerFactory;
  }

  @Bean
  public SchedulerFactoryBean schedulerFactory(
    SpringBeanJobFactory springBeanJobFactory,
    Trigger trigger
  ) {
    SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
    schedulerFactory.setAutoStartup(true);
    schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
    schedulerFactory.setTriggers(trigger);

    springBeanJobFactory.setApplicationContext(applicationContext);
    schedulerFactory.setJobFactory(springBeanJobFactory);

    return schedulerFactory;
  }
}