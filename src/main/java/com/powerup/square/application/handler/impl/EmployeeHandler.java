package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.employee.EmployeeRequest;
import com.powerup.square.application.dto.employee.EmployeeResponse;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.application.mapper.*;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeHandler implements IEmployeeHandler {
    private final IEmployeeServicePort iEmployeeServicePort;
    private final IEmployeeRequestMapper iEmployeeRequestMapper;

    private final IEmployeeResponseMapper iEmployeeResponseMapper;

    private final IRestaurantServicePort iRestaurantServicePort;

    public EmployeeHandler(IEmployeeServicePort iEmployeeServicePort, IEmployeeRequestMapper iEmployeeRequestMapper, IEmployeeResponseMapper iEmployeeResponseMapper, IRestaurantServicePort iRestaurantServicePort) {
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.iEmployeeRequestMapper = iEmployeeRequestMapper;
        this.iEmployeeResponseMapper = iEmployeeResponseMapper;
        this.iRestaurantServicePort = iRestaurantServicePort;
    }

    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = iEmployeeRequestMapper.ToEmployee(employeeRequest);

        employee.setIdUser(employee.getIdUser());
        employee.setRestaurant(iRestaurantServicePort.getRestaurant(employee.getIdRestaurant()));

        iEmployeeServicePort.saveEmployee(employee);
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        Employee employee = iEmployeeServicePort.getEmployee(id);
        return iEmployeeResponseMapper.toEmployeeResponse(employee);
    }

}
