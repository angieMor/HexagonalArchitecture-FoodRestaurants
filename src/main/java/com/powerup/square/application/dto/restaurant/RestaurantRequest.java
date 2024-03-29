package com.powerup.square.application.dto.restaurant;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RestaurantRequest {
    @NotBlank(message = "The field name should have data")
    @NotNull(message = "Invalid name: name is NULL")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "The field name shouldn't have numbers")
    private String name;

    @NotNull(message = "Invalid address: address is NULL")
    @NotBlank (message = "The field address is mandatory")
    private String address;
    private Long idOwner;
    @NotBlank (message = "The field phone is mandatory")
    @NotNull(message = "Invalid phone: phone is NULL")
    @Pattern(regexp = "^(\\+57)?3\\d{9}$", message = "The phone must be numeric")
    private String phone;

    @NotNull(message = "Invalid url: url is NULL")
    @NotBlank (message = "The url field is mandatory")
    @URL(message = "must be a url")
    private String urlLogo;

    @NotNull(message = "Invalid nit: nit is NULL")
    @Pattern(regexp = "^(\\d*[1-9]+\\d*\\.?\\d*|\\d*\\.\\d*[1-9]+\\d*)$", message = "The NIT must be numeric and positive")
    @NotBlank (message = "The field NIT is mandatory")
    private String nit;
}
