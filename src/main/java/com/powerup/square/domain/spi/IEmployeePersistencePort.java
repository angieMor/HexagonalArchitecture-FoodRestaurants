package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.Employee;

import java.util.List;

public interface IEmployeePersistencePort {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployee(Long id);
}
