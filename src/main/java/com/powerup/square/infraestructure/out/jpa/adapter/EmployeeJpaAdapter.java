package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.spi.IEmployeePersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.EmployeeEntity;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IEmployeeMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IEmployeeRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeJpaAdapter implements IEmployeePersistencePort {
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public void saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employee);
        employeeEntity.setRestaurant(restaurantRepository.findByIdOwner(employee.getIdRestaurant()));
        employeeEntity.setIdUser(employee.getIdUser());

//        employeeEntity.setRestaurant(restaurantRepository.findById(employee.getIdRestaurant()).get());

        employeeRepository.save(employeeEntity);
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
