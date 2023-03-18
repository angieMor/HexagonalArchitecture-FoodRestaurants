package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Order;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {

//    @Mappings({
//            @Mapping(target="id", source="id"),
//            @Mapping(target="idClient", source="idClient"),
//            @Mapping(target="date", source="date"),
//            @Mapping(target="state", source="state")
//    })

    OrderEntity toEntity(Order order);

    Order toOrder(OrderEntity orderEntity);

    List<Order> toOrder(List<OrderEntity> orderEntity);


}
