package com.powerup.square.infraestructure.out.jpa.repository;

import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderPlatesRepository extends JpaRepository<OrderPlatesEntity, Long> {

//    @Query(value = "SELECT * FROM public.order_plates where id_orders = :idOrder", nativeQuery = true)
//    List<OrderPlatesEntity> findAllByOrder(@Param("idOrders") Long idOrder);

}
