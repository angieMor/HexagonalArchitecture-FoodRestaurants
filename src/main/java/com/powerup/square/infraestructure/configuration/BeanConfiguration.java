package com.powerup.square.infraestructure.configuration;

import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.domain.usecase.PlateUseCase;
import com.powerup.square.domain.usecase.RestaurantUseCase;
import com.powerup.square.infraestructure.out.jpa.adapter.PlateJpaAdapter;
import com.powerup.square.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.powerup.square.infraestructure.out.jpa.mapper.ICategoryMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.ICategoryRepository;
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
    public IPlateServicePort plateServicePort() { return new PlateUseCase(platePersistencePort());}



}
