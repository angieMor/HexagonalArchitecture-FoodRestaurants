package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.EmployeeResponse;
import com.powerup.square.application.dto.PlateResponse;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeResponseMapper {

    EmployeeResponse toEmployeeResponse(Employee employee);

}
