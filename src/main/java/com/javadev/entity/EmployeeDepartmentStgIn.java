package com.javadev.entity;

import com.javadev.constant.CustBatchStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE_DEPT_STG_IN")
public class EmployeeDepartmentStgIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;
    @Column(name = "AGE")
    private int age;
    @Column(name="DEPARTMENT_NAME")
    private String departmentName;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private CustBatchStatus status;
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public CustBatchStatus getStatus() {
        return status;
    }

    public void setStatus(CustBatchStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EmployeeDepartmentStgIn{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", age=" + age +
                ", departmentName='" + departmentName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
