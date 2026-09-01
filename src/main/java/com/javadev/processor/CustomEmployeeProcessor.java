package com.javadev.processor;

import com.javadev.entity.Department;
import com.javadev.entity.Employee;
import com.javadev.entity.EmployeeDepartmentStgIn;
import com.javadev.repository.DepartmentRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomEmployeeProcessor extends CompositeItemProcessor<EmployeeDepartmentStgIn, Employee> {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public @Nullable Employee process(EmployeeDepartmentStgIn item) throws Exception {
        System.out.println("Processing EmployeeDepartmentStgIn -> Employee"+ item.toString());
        Employee employee = new Employee();
        employee.setAge(item.getAge());
        // Create probe with the target name
        Department probe = new Department();
        probe.setDepartmentName("Engineering");

        Example<Department> example = Example.of(probe);

        // Fetch using findBy
        Department department = departmentRepository.findBy(
                example,
                query -> Objects.requireNonNull(query.first().orElse(null))
        );

        employee.setDepartment(department);
        employee.setName(item.getEmployeeName());
        return employee;
    }


}
