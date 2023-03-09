package com.powerup.square.infraestructure.configuration;


import com.powerup.square.application.mapper.IEmployeeRequestMapper;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.spi.*;
import com.powerup.square.domain.usecase.*;
import com.powerup.square.infraestructure.out.jpa.adapter.*;
import com.powerup.square.infraestructure.out.jpa.mapper.*;
import com.powerup.square.infraestructure.out.jpa.repository.*;
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
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;

    private final IOrderPlatesRepository orderPlatesRepository;
    private final IOrderPlatesMapper orderPlatesMapper;



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
        return new EmployeeJpaAdapter(employeeRepository, employeeMapper, restaurantRepository);
    }

    @Bean
    public IEmployeeServicePort employeeServicePort(){
        return new EmployeeUseCase(employeePersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }

    @Bean
    public IOrderPlatesPersistencePort orderPlatesPersistencePort(){
        return new OrderPlatesJpaAdapter(orderPlatesRepository, orderPlatesMapper);
    }

    @Bean
    public IOrderPlatesServicePort orderPlatesServicePort(){
        return new OrderPlatesUseCase(orderPlatesPersistencePort());
    }


}
