package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.order.OrderGeneralRequest;
import com.powerup.square.application.dto.order.OrderGeneralResponse;
import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.application.handler.impl.OrderHandler;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderHandler orderHandler;

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content)
    })
    @PostMapping("/createOrder")
    public ResponseEntity<Void> saveOrderEntity(@Validated @RequestBody OrderGeneralRequest orderGeneralRequest){
        orderHandler.saveOrder(orderGeneralRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/ordersByState")
    public ResponseEntity<List<OrderGeneralResponse>> getOrdersByState(
            @Validated @RequestBody OrdersStateRequest ordersStateRequest,
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getAllOrdersByState(page, size, ordersStateRequest));
    }


}
