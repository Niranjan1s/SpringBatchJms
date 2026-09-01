package com.javadev.jmslistener;

import org.springframework.batch.core.job.Job;

import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSCustomListener {

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    @Qualifier("simpleJob")
    private Job job;


    @JmsListener(destination = "jms/employeedeptqueue")
    public void receiveMessage(String message) throws JobInstanceAlreadyCompleteException, InvalidJobParametersException, JobExecutionAlreadyRunningException, JobRestartException {
        System.out.println(message);
        JobParameters params = new JobParametersBuilder()
                .addString("messagePayload", message)
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        jobOperator.start(job, params);
    }
}
