package com.javadev.repository;

import com.javadev.entity.EmployeeDepartmentStgIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDepartmentStgInRepository extends JpaRepository<EmployeeDepartmentStgIn,Integer> {

    @Query(value = "SELECT COUNT(e) FROM EmployeeDepartmentStgIn e WHERE e.status = 'NEW'")
    long findByQuery();
}
