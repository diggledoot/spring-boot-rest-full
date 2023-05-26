package com.example.restservicefull.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

  @Autowired
  private Scheduler scheduler;

  public Scheduler getScheduler() {
    return this.scheduler;
  }

  public void stopAllJobs() {}

  public void stopJob(String jobName) {}

  public void startAllJobs() throws SchedulerException {
    //TODO: Probably call the JobRepo and instantiate with jobclass and cron expression
    JobDetail job = JobBuilder
      .newJob(DummyJob.class)
      .withIdentity("dummjob", "my scheduler group")
      .build();

    Trigger trigger = TriggerBuilder
      .newTrigger()
      .withIdentity("triggerdummy", "triggergroup")
      .startNow()
      .withSchedule(
        SimpleScheduleBuilder
          .simpleSchedule()
          .withIntervalInSeconds(5)
          .repeatForever()
      )
      .build();

    this.scheduler.scheduleJob(job, trigger);
  }

  public void startJob(String jobName) {}

  public void changeCronForJob(String cron, String jobName) {}
}
