package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.plate.*;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/plates")
@RequiredArgsConstructor
public class PlateRestController {

    private final IPlateHandler plateHandler;
    private final UserClient userClient;

    @Operation(summary = "Add a new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Plate already exists", content = @Content)
    })
    @PostMapping("/createPlate")
    public ResponseEntity<Void> savePlateEntity(@Validated @RequestBody PlateRequest plateRequest,
                                                @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        // Getting info from token
        String token1 = token.replace("Bearer ", "");

        // Split into 3 parts with . delimiter
        String[] parts = token1.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(parts[1]));

        //Accessing to the Json String info
        JSONObject jsonObject = new JSONObject(payload);
        String proprietaryEmail = (String) jsonObject.get("sub");

        Long idOwner = userClient.getUserByEmail(proprietaryEmail).getId();
        plateHandler.savePlate(plateRequest, idOwner);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
    public ResponseEntity<Void> editPlate(@Validated @RequestBody PlateUpdatingRequest plateUpdatingRequest,
                                          @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        // Getting info from token
        String token1 = token.replace("Bearer ", "");

        // Split into 3 parts with . delimiter
        String[] parts = token1.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(parts[1]));

        //Accessing to the Json String info
        JSONObject jsonObject = new JSONObject(payload);
        String proprietaryEmail = (String) jsonObject.get("sub");

        Long idOwner = userClient.getUserByEmail(proprietaryEmail).getId();

        plateHandler.updatePlate(plateUpdatingRequest, idOwner);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "change plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate state updated successfully", content = @Content)
    })
    @PutMapping("/putActivate")
    public ResponseEntity<Void> editPlateStatus(@Validated @RequestBody PlateIsActiveRequest plateIsActiveRequest,
                                                @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        // Getting info from token
        String token1 = token.replace("Bearer ", "");

        // Split into 3 parts with . delimiter
        String[] parts = token1.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(parts[1]));

        //Accessing to the Json String info
        JSONObject jsonObject = new JSONObject(payload);
        String proprietaryEmail = (String) jsonObject.get("sub");

        Long idOwner = userClient.getUserByEmail(proprietaryEmail).getId();

        plateHandler.isActivePlate(plateIsActiveRequest, idOwner);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Get plates by restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate gotten", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content)
    })
    @PostMapping("/allPlates")
    public ResponseEntity<List<PlateResponse>> getPlatesFromRestaurant(@Validated @RequestBody PlateListRequest plateListRequest){
        return ResponseEntity.status(HttpStatus.OK).body(plateHandler.getPlatesFromRestaurant(plateListRequest));

    }

}
