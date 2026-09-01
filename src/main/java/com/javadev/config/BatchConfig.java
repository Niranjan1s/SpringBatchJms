package com.javadev.config;


import com.javadev.entity.Employee;
import com.javadev.entity.EmployeeDepartmentStgIn;
import com.javadev.processor.CustomEmployeeProcessor;
import com.javadev.util.EmployeeDepartmentStgInRowMapper;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public Job simpleJob(JobRepository jobRepository, Step sampleStep, JobExecutionListener jobExecutionListener) {
        return new JobBuilder("sampleEmployeeJob", jobRepository)
                .listener(jobExecutionListener)
                .start(sampleStep)
                .build();

    }
    @Bean
    public Step sampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcCursorItemReader<EmployeeDepartmentStgIn> employeeStagReader, CustomEmployeeProcessor customEmployeeProcessor, ItemWriter<Employee> itemWriter) {
        return new StepBuilder(jobRepository)
                .<EmployeeDepartmentStgIn, Employee>chunk(10).transactionManager(transactionManager)
                .reader(employeeStagReader)
                .processor(customEmployeeProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<EmployeeDepartmentStgIn> itemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<EmployeeDepartmentStgIn>()
                .dataSource(dataSource)
                .name("employeeStagReader")
                .sql("select * from EMPLOYEE_DEPT_STG_IN where STATUS='NEW' OR STATUS='FAILED' ")
                .rowMapper(new EmployeeDepartmentStgInRowMapper())
                .build();

    }

    @Bean
    public JdbcBatchItemWriter<Employee> itemWriter(DataSource dataSource) {
        return  new JdbcBatchItemWriterBuilder<Employee>()
                .dataSource(dataSource)
                .sql("INSERT INTO employee ( name,age, department) VALUES (:name, :age,:department)")
                .beanMapped()
                .build();
    }


}
