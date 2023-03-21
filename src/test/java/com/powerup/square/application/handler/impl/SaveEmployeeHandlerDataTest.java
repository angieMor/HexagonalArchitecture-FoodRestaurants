package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.employee.EmployeeRequest;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Restaurant;

public class SaveEmployeeHandlerDataTest {

    public static Employee obtainEmployee(){
        return new Employee(
                new Restaurant(
                        1L,
                        "Angus Hamburguers",
                        "Street 25",
                        10L,
                        "3013218520",
                        "www.logo.es",
                        "ASD-121854-YU"
                ),
                1L,
                "Employee"
        );
    }

    public static EmployeeRequest obtainEmployeeRequest(){
        EmployeeRequest employeeRequest = new EmployeeRequest();

        employeeRequest.setIdRestaurant(1L);
        employeeRequest.setField("Employee");
        employeeRequest.setIdUser(1L);

        return employeeRequest;
    }

}
