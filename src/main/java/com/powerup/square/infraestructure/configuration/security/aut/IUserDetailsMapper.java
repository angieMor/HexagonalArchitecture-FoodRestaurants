package com.powerup.square.infraestructure.configuration.security.aut;

import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.dto.user.securityDto.UserAuthDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserDetailsMapper {
    @Mapping(source = "userResponse.role.name", target = "role")
    DetailsUser toUser(UserResponse userResponse);
    UserAuthDto toUserAuth(DetailsUser user);

}
