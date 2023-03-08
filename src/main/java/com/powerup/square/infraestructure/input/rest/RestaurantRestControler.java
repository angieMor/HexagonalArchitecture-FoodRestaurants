package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.application.dto.RestaurantRequest;
import com.powerup.square.application.dto.RestaurantResponse;
import com.powerup.square.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantRestControler {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/createRestaurant")
    public ResponseEntity<Void> saveRestaurantEntity(@Validated @RequestBody RestaurantRequest restaurantRequest){
        restaurantHandler.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Restaurant do not exists", content = @Content)
    })
    @GetMapping("/GET/{id}")
    public RestaurantResponse getAllRestaurantById(@PathVariable Long id){
        return restaurantHandler.getRestaurant(id);
    }
    @Operation(summary = "Get restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant gotten", content = @Content),
            @ApiResponse(responseCode = "400", description = "Restaurant do not exists", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant(@Validated @RequestBody RestaurantListRequest restaurantListRequest){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantHandler.getAllRestaurant(restaurantListRequest));

    }

}
