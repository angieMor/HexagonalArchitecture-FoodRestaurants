package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class RestaurantJpaAdapterTest {

    @InjectMocks
    RestaurantJpaAdapter restaurantJpaAdapter;

    @Mock
    IRestaurantRepository restaurantRepository;

    @Mock
    IRestaurantMapper restaurantMapper;

    @Test
    void saveRestaurant() {

        Restaurant restaurant = SaveRestaurantJpaAdapterDataTest.obtainRestaurant();
        RestaurantEntity restaurantEntity = SaveRestaurantJpaAdapterDataTest.obtainRestaurantEntity();

        when(restaurantMapper.toEntity(restaurant)).thenReturn(restaurantEntity);
        restaurantJpaAdapter.saveRestaurant(restaurant);

        verify(restaurantRepository).save(restaurantEntity);

    }

    @Test
    void getAllRestaurant() {
    }

//    @Test
//    void getRestaurant() {
//        restaurantJpaAdapter.getRestaurant(anyLong());
//
//        verify(restaurantMapper).toRestaurant(restaurantRepository.findById(anyLong()).get());
//    }

    @Test
    void getRestaurantByIdOwner() {
        restaurantJpaAdapter.getRestaurantByIdOwner(anyLong());

        verify(restaurantMapper).toRestaurant(restaurantRepository.findByIdOwner(anyLong()));
    }

    @Test
    void existByName() {
        restaurantJpaAdapter.existByName(any());

        verify(restaurantRepository).existsByName(any());
    }

    @Test
    void existById() {
        restaurantJpaAdapter.existById(anyLong());

        verify(restaurantRepository).existsById(anyLong());
    }

    @Test
    void existByIdOwner() {
        restaurantJpaAdapter.existByIdOwner(anyLong());

        verify(restaurantRepository).existsByIdOwner(anyLong());
    }
}