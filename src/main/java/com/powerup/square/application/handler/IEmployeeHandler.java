package com.powerup.square.application.handler;

import com.powerup.square.application.dto.employee.EmployeeRequest;
import com.powerup.square.application.dto.employee.EmployeeResponse;

public interface IEmployeeHandler {
    void saveEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse getEmployee(Long id);
}
