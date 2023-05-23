package com.example.restservicefull.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

@Component
public class QuartzJob implements Job {

  @Override
  public void execute(JobExecutionContext context)
    throws JobExecutionException {
    System.out.println("Job executed!");

    try {
      Class<?> jobClass = Class.forName(
        "com.example.restservicefull.quartz.Job1"
      );
      Job jobInstance = (Job) jobClass.newInstance();

      JobDetail jobDetail = JobBuilder
        .newJob((Class<? extends Job>) jobClass)
        .withIdentity("Job1")
        .build();

      Trigger trigger = TriggerBuilder
        .newTrigger()
        .withIdentity("Job1" + "Trigger")
        .startNow()
        .build();

      Scheduler scheduler = context.getScheduler();
      scheduler.scheduleJob(jobDetail, trigger);

      // Execute the job immediately
      scheduler.triggerJob(jobDetail.getKey());
    } catch (
      ClassNotFoundException
      | InstantiationException
      | IllegalAccessException
      | SchedulerException e
    ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
