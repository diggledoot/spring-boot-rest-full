package com.example.restservicefull.quartz;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
      Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(
        "com.example.restservicefull.quartz.Job1"
      );
      Constructor<? extends Job> jobConstructor = jobClass.getDeclaredConstructor();
      jobConstructor.newInstance();

      JobDetail jobDetail = JobBuilder
        .newJob(jobClass)
        .withIdentity("Job1", "JobGroup")
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
      | SchedulerException
      | IllegalArgumentException
      | InvocationTargetException
      | NoSuchMethodException
      | SecurityException e
    ) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
