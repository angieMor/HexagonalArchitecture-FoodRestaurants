package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.RestaurantRequest;
import com.powerup.square.application.handler.IEmployeeHandler;
import com.powerup.square.infraestructure.out.jpa.mapper.IEmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final IEmployeeHandler employeeHandler;
    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "employee already exists", content = @Content)
    })
    @PostMapping("/createEmployee")
    public ResponseEntity<Void> saveEmployeeEntity(@Validated @RequestBody EmployeeRequest employeeRequest){
//        restaurantHandler.saveRestaurant(restaurantRequest);
        employeeHandler.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
