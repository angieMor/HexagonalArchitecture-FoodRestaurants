package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.EmployeeResponse;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.application.mapper.*;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeHandler implements IEmployeeHandler {
    private final IEmployeeServicePort iEmployeeServicePort;
    private final IEmployeeRequestMapper iEmployeeRequestMapper;

    private final IEmployeeResponseMapper iEmployeeResponseMapper;

    public EmployeeHandler(IEmployeeServicePort iEmployeeServicePort, IEmployeeRequestMapper iEmployeeRequestMapper, IEmployeeResponseMapper iEmployeeResponseMapper) {
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.iEmployeeRequestMapper = iEmployeeRequestMapper;
        this.iEmployeeResponseMapper = iEmployeeResponseMapper;
    }

    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = iEmployeeRequestMapper.ToEmployee(employeeRequest);
        employee.setIdRestaurant(employee.getIdRestaurant());
        employee.setIdUser(employee.getIdUser());

//        Restaurant restaurant = res
//        employee.setIdRestaurant();
        iEmployeeServicePort.saveEmployee(employee);
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        return null;
    }

}
