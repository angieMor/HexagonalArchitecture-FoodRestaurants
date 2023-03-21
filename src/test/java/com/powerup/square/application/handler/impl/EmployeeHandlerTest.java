package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.employee.EmployeeRequest;
import com.powerup.square.application.mapper.IEmployeeRequestMapper;
import com.powerup.square.application.mapper.IEmployeeResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeHandlerTest {

    @InjectMocks
    EmployeeHandler employeeHandler;

    @Mock
    IEmployeeServicePort iEmployeeServicePort;

    @Mock
    IEmployeeRequestMapper iEmployeeRequestMapper;

    @Mock
    IEmployeeResponseMapper iEmployeeResponseMapper;

    @Mock
    IRestaurantServicePort iRestaurantServicePort;

    @Test
    void saveEmployee(){
        Employee employee = SaveEmployeeHandlerDataTest.obtainEmployee();
        EmployeeRequest employeeRequest = SaveEmployeeHandlerDataTest.obtainEmployeeRequest();

        when(iEmployeeRequestMapper.ToEmployee(employeeRequest)).thenReturn(employee);

        employeeHandler.saveEmployee(employeeRequest);

        verify(iEmployeeServicePort).saveEmployee(employee);
    }

}
