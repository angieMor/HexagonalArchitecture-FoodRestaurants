package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {

    boolean existsByName(String name);

    List<PlateEntity> findPlatesByRestaurantId(Long restaurantId, Pageable pageable);

//    boolean findByActive(Boolean active);

}
