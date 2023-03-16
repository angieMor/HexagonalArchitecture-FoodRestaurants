package com.powerup.square.infraestructure.configuration;


import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.spi.*;
import com.powerup.square.domain.usecase.*;
import com.powerup.square.infraestructure.configuration.security.aut.DetailsUser;
import com.powerup.square.infraestructure.configuration.security.aut.IUserDetailsMapper;

import com.powerup.square.infraestructure.configuration.userclient.UserClient;
import com.powerup.square.infraestructure.out.jpa.adapter.*;
import com.powerup.square.infraestructure.out.jpa.mapper.*;
import com.powerup.square.infraestructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


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

    private final UserClient userClient;

    private final IUserDetailsMapper userDetailsMapper;

    @Bean
    @Primary
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){

        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Primary
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

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> optionalDetailsUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Optional<DetailsUser> optionalDetailsUser(String username) {
        UserResponse userResponse = userClient.getUserByEmail(username);
        DetailsUser user = userDetailsMapper.toUser(userResponse);
        user.setRole(userResponse.getRole().getName());
        return Optional.of(user);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
