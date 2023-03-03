package com.powerup.square.application.handler;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.EmployeeResponse;

public interface IEmployeeHandler {
    void saveEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse getEmployee(Long id);
}
