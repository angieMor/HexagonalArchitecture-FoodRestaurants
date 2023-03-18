package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Employee;
import com.powerup.square.infraestructure.out.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeMapper {

    EmployeeEntity toEntity(Employee employee);
    Employee toEmployee(EmployeeEntity employeeEntity);
}
