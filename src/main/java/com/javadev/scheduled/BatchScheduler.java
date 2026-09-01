package com.javadev.scheduled;

import com.javadev.entity.EmployeeDepartmentStgIn;
import com.javadev.repository.EmployeeDepartmentStgInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

    @Autowired
    JmsTemplate  jmsTemplate;

    @Autowired
    EmployeeDepartmentStgInRepository employeeDepartmentStgInRepository;

    @Scheduled(fixedRate = 10000)
    public void scheduled() {
        System.out.println("scheduled");

       long count=employeeDepartmentStgInRepository.findByQuery();
       if(count>0){
           jmsTemplate.convertAndSend("available");
       }

    }
}
