package com.example.restservicefull.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class Job1 implements Job {

  @Override
  public void execute(JobExecutionContext context)
    throws JobExecutionException {
    System.out.println("Job1 printing!");
  }
}
