package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.*;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
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
@RequestMapping("/plates")
@RequiredArgsConstructor
public class PlateRestController {

    private final IPlateHandler plateHandler;

    @Operation(summary = "Add a new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Plate already exists", content = @Content)
    })
    @PostMapping("/createPlate")
    public ResponseEntity<Void> savePlateEntity(@Validated @RequestBody  PlateRequest plateRequest){
        plateHandler.savePlate(plateRequest);
        return new ResponseEntity("Plate created", HttpStatus.CREATED);
    }
    @Operation(summary = "Get plates by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate gotten", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content)
    })
    @GetMapping("/getPlate/{id}")
    public PlateResponse getAllPlateById(@PathVariable Long id){
        return plateHandler.getPlate(id);
    }


    @Operation(summary = "change plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate updated successfully", content = @Content)
    })
    @PutMapping("/putPlate")
    public ResponseEntity<Void> editPlate(@RequestBody PlateUpdatingRequest plateUpdatingRequest){
        plateHandler.updatePlate(plateUpdatingRequest);
        return new ResponseEntity("Plate updated succesfully",HttpStatus.OK);
    }

    @Operation(summary = "change plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate state updated successfully", content = @Content)
    })
    @PutMapping("/putActivate")
    public ResponseEntity<Void> editPlateStatus(@RequestBody PlateIsActiveRequest plateIsActiveRequest){
        plateHandler.isActivePlate(plateIsActiveRequest);
        return new ResponseEntity("Plate status updated",HttpStatus.OK);
    }

    @Operation(summary = "Get plates by restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate gotten", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content)
    })
    @PostMapping("/allPlates")
    public ResponseEntity<List<PlateResponse>> getPlatesFromRestaurant(@RequestBody PlateListRequest plateListRequest){
        return ResponseEntity.status(HttpStatus.OK).body(plateHandler.getPlatesFromRestaurant(plateListRequest));

    }

}
