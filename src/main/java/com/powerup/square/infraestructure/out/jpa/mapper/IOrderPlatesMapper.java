package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderPlatesMapper {
    OrderPlatesEntity toEntity(OrderPlates orderPlates);
    OrderPlates toOrderPlates(Optional<OrderPlatesEntity> orderPlatesEntity);
}
