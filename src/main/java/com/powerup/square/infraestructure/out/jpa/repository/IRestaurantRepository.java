package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    boolean existsByName(String name);
    boolean existsByIdOwner(Long idOwner);
    RestaurantEntity findByIdOwner(Long idOwner);
}
