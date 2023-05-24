package com.example.restservicefull.quartz_basics;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

  @Override
  public void execute(JobExecutionContext context)
    throws JobExecutionException {
    System.out.println("Sample job ran!");
  }
}
