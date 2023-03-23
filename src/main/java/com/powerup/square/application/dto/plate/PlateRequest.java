package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PlateRequest {

    @NotBlank(message = "Field 'name' can't be blank")
    private String name;

    private Long idCategory;
    @NotBlank(message = "Field 'description' can't be blank")
    private String description;
    @NotNull(message = "Field 'price' can't be null")
    private Long price;
    @NotBlank(message =  "Field required")
    @URL(message = "must be a url")
    private String urlImage;

}
