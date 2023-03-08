package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.RestaurantResponse;
import com.powerup.square.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

}
