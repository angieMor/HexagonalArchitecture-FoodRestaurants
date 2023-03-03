package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.EmployeeResponse;
import com.powerup.square.application.dto.RestaurantRequest;
import com.powerup.square.application.dto.RestaurantResponse;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.application.mapper.IRestaurantRequestMapper;
import com.powerup.square.application.mapper.IRestaurantResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeHandler /*implements IEmployeeHandler */{
//    private final IEmployeeServicePort iEmployeeServicePort;
//
//    public EmployeeHandler(IEmployeeServicePort iEmployeeServicePort) {
//        this.iEmployeeServicePort = iEmployeeServicePort;
//    }

//    @Override
//    public void saveRestaurant(RestaurantRequest restaurantRequest) {
//        Restaurant restaurant = iRestaurantRequestMapper.toRestaurant(restaurantRequest);
//        iRestaurantServicePort.saveRestaurant(restaurant);
//    }
//
//    @Override
//    public RestaurantResponse getRestaurant(Long id) {
//        Restaurant restaurant = iRestaurantServicePort.getRestaurant(id);
//        return iRestaurantResponseMapper.toRestaurantResponse(restaurant);
//    }
//
//    @Override
//    public void saveEmployee(EmployeeRequest employeeRequest) {
//        Employee employee = new Employee(employeeRequest.getIdOwner(), employeeRequest.getIdUser(), employeeRequest.getField());
//        iEmployeeServicePort.saveEmployee(employee);
//    }
//
//    @Override
//    public EmployeeResponse getEmployee(Long id) {
//        Employee employee = iEmployeeServicePort.getEmployee(id);
//        EmployeeResponse employeeResponse = new EmployeeResponse();
//        employeeResponse.setIdUser(employee.getIdUser());
//        employeeResponse.setIdOwner(employee.getIdOwner());
//        employeeResponse.setField(employee.getField());
//        return employeeResponse;
//    }
}
