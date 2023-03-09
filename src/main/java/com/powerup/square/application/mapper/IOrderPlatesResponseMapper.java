package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.application.dto.OrderGeneralResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderPlatesResponseMapper {

    OrderGeneralResponse toOrderPlatesResponse(OrderGeneralRequest orderGeneralRequest);

}
