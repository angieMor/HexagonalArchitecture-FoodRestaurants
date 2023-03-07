package com.powerup.square.infraestructure.configuration;


import com.powerup.square.application.mapper.IEmployeeRequestMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.spi.IEmployeePersistencePort;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.domain.usecase.EmployeeUseCase;
import com.powerup.square.domain.usecase.PlateUseCase;
import com.powerup.square.domain.usecase.RestaurantUseCase;
import com.powerup.square.infraestructure.out.jpa.adapter.EmployeeJpaAdapter;
import com.powerup.square.infraestructure.out.jpa.adapter.PlateJpaAdapter;
import com.powerup.square.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.powerup.square.infraestructure.out.jpa.mapper.ICategoryMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IEmployeeMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.ICategoryRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IEmployeeRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IPlateRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantMapper restaurantMapper;
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;
    private final ICategoryMapper categoryMapper;
    private final ICategoryRepository categoryRepository;
//    private final IEmployeeServicePort employeeServicePort;
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;

    @Bean
    @Primary
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){

        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IPlatePersistencePort platePersistencePort(){
        return new PlateJpaAdapter(plateRepository, plateMapper);
    }

    @Bean
    public IPlateServicePort plateServicePort() {
        return new PlateUseCase(platePersistencePort());
    }

    @Bean
    public IEmployeePersistencePort employeePersistencePort(){
        return new EmployeeJpaAdapter(employeeRepository, employeeMapper);
    }

    @Bean
    public IEmployeeServicePort employeeServicePort(){
        return new EmployeeUseCase(employeePersistencePort());
    }

}
