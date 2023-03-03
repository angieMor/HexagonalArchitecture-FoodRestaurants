package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Order;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {
    OrderEntity toEntity(Order order);
    Order toOrder(Optional<OrderEntity> orderEntity);
}
