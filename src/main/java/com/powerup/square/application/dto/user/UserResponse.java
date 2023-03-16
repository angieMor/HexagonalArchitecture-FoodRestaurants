package com.powerup.square.application.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Role role;
    private String idDocument;

}
