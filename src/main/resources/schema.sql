CREATE TABLE EMPLOYEE_DEPT_STG_IN (
                                      ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      EMPLOYEE_NAME VARCHAR(40),
                                      AGE INT,
                                      DEPARTMENT_NAME VARCHAR(50),
                                      STATUS VARCHAR(20),
                                      CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

