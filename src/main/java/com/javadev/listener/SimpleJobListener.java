package com.javadev.listener;

import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleJobListener  implements JobExecutionListener {

   public void beforeJob(JobExecution jobExecution){
        System.out.println
                ("beforeJob:"+ jobExecution.getJobInstance()+" Job Parameters:"+jobExecution.getJobParameters()+" JobExecutionId:"+jobExecution.getId()+" JobInstance Id:"+jobExecution.getJobInstance().getId());

       System.out.println("Execution Context:"+jobExecution.getExecutionContext());
    }

   public void afterJob(JobExecution jobExecution){
        System.out.println
                ("beforeJob:"+ jobExecution.getJobInstance()+" Job Parameters:"+jobExecution.getJobParameters()+" JobExecutionId:"+jobExecution.getId()+" JobInstance Id:"+jobExecution.getJobInstance().getId());
       System.out.println("Execution Context:"+jobExecution.getExecutionContext());
    }

}
