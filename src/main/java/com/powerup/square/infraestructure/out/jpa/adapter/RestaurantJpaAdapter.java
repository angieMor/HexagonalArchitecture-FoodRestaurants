package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.domain.exception.RestaurantAlreadyExistsException;
import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
@Service
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantMapper restaurantMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {

        if(restaurantRepository.findByNit(restaurant.getNit()).isPresent()){
            throw new RestaurantAlreadyExistsException();
        }

        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(restaurant);
        restaurantEntity.setId(-1L);
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurant(RestaurantListRequest restaurantListRequest) {
        Pageable pageable = PageRequest.of(restaurantListRequest.getPage().intValue(),
                restaurantListRequest.getAmount().intValue(),
                Sort.by(Sort.Direction.ASC, "name"));

        return restaurantRepository.findAll(pageable).stream().map(restaurantMapper::toRestaurant).collect(Collectors.toList());
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantMapper.toRestaurant(restaurantRepository.findById(id).get());
    }

    @Override
    public Restaurant getRestaurantByIdOwner(Long idOwner) {
        return restaurantMapper.toRestaurant(restaurantRepository.findByIdOwner(idOwner));
    }

    @Override
    public boolean existByName(String name) {
        return restaurantRepository.existsByName(name);
    }

    @Override
    public boolean existById(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public boolean existByIdOwner(Long idOwner) {
        return restaurantRepository.existsByIdOwner(idOwner);
    }

}
