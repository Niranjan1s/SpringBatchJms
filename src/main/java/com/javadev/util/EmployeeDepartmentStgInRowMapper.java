package com.javadev.util;

import com.javadev.constant.CustBatchStatus;
import com.javadev.entity.EmployeeDepartmentStgIn;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDepartmentStgInRowMapper implements RowMapper<EmployeeDepartmentStgIn> {

    public static final String ID_COLUMN = "ID";
    public static final String NAME_COLUMN = "EMPLOYEE_NAME";
    public static final String AGE_COLUMN = "AGE";
    public static final String DEPARTMENTNAME_COLUMN = "DEPARTMENT";
    public static final String STATUS_COLUMN = "STATUS";
    public static final String CREATEDDATE_COLUMN = "CREATED_AT";
    @Override
    public EmployeeDepartmentStgIn mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeDepartmentStgIn employeeDepartmentStgIn = new EmployeeDepartmentStgIn();

        employeeDepartmentStgIn.setId(rs.getInt(ID_COLUMN));
        employeeDepartmentStgIn.setDepartmentName(rs.getString(DEPARTMENTNAME_COLUMN));
        employeeDepartmentStgIn.setAge(rs.getInt(AGE_COLUMN));
        employeeDepartmentStgIn.setEmployeeName(rs.getString(NAME_COLUMN));
        String statusStr = rs.getString(STATUS_COLUMN);
        if (statusStr != null) {
            employeeDepartmentStgIn.setStatus(CustBatchStatus.valueOf(statusStr));
        }
        employeeDepartmentStgIn.setCreatedAt(rs.getDate(CREATEDDATE_COLUMN).toLocalDate());

        return employeeDepartmentStgIn;
    }
}
