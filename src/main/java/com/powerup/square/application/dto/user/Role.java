package com.powerup.square.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private String description;
}
