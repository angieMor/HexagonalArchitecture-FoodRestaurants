package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class PlateRequest {

    @NotBlank(message = "Field 'name' can't be blank")
    private String name;

    private Long idCategory;
    @NotBlank(message = "Field 'description' can't be blank")
    private String description;
    @Pattern(regexp = "^(\\d*[1-9]+\\d*\\.?\\d*|\\d*\\.\\d*[1-9]+\\d*)$", message = "The price must be numeric and positive")
    @NotNull(message = "Field 'price' can't be null")
    private Long price;
    @NotBlank(message =  "Field required")
    @URL(message = "must be a url")
    private String urlImage;

}
