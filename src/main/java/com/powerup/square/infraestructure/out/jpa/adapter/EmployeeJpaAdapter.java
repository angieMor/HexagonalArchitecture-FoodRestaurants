package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.spi.IEmployeePersistencePort;
import com.powerup.square.infraestructure.out.jpa.mapper.IEmployeeMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeJpaAdapter implements IEmployeePersistencePort {
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;

    @Override
    public void saveEmployee(Employee employee) {

    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public Employee getEmployee(Long id) {
        return null;
    }
}
