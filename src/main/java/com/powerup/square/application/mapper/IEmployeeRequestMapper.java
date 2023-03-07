package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRequestMapper {

    @Mappings({
            @Mapping(target="idUser", source="idUser"),
            @Mapping(target="field", source="field")
    })
    Employee ToEmployee(EmployeeRequest employeeRequest);

}
