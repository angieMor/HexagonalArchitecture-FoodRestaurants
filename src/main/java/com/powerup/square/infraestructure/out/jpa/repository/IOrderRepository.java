package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.domain.model.Order;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity getOrderByIdClient(Long idClient);

    boolean existsByIdClient(Long idClient);

}
