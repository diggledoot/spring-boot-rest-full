package com.example.restservicefull.quartz;

import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

  private final Scheduler scheduler;

  public SchedulerService(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  public Scheduler getScheduler() {
    return this.scheduler;
  }

  public void stopAllJobs() {}

  public void stopJob(String jobName) {}

  public void startAllJobs() {}

  public void startJob(String jobName) {}

  public void changeCronForJob(String cron, String jobName) {}
}
