package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.domain.model.Order;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity getOrderByIdClient(Long idClient);

    boolean existsByIdClient(Long idClient);

    @Query(value = "SELECT * FROM public.orders WHERE state = :state AND id_restaurant = :idRestaurant", nativeQuery = true)
    List<OrderEntity> getOrdersByState(@Param("state") String state, @Param("idRestaurant")Long idRestaurant, Pageable pageable);

    OrderEntity getOrderById(Long idOrder);



}
