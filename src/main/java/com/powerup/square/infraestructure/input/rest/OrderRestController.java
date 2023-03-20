package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.order.*;
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
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/createOrder")
    public ResponseEntity<Void> saveOrderEntity(@Validated @RequestBody OrderGeneralRequest orderGeneralRequest){
        orderHandler.saveOrder(orderGeneralRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Filter orders by state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders filtered by state", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/ordersByState")
    public ResponseEntity<List<OrderGeneralResponse>> getOrdersByState(
            @Validated @RequestBody OrdersStateRequest ordersStateRequest,
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.getAllOrdersByState(page, size, ordersStateRequest));
    }

    @Operation(summary = "Assign yourself some orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "", content = @Content),
            @ApiResponse(responseCode = "400", description = "", content = @Content)
    })
    @PostMapping("/asignOrder")
    public ResponseEntity<Void> assignOrderEntity(@Validated @RequestBody OrderUpdateRequest orderUpdateRequest){
        orderHandler.updateOrderToAsignIt(orderUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Notifies to the client that the order is ready via SMS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "", content = @Content),
            @ApiResponse(responseCode = "400", description = "", content = @Content)
    })
    @PostMapping("/notifyOrderIsReady")
    public ResponseEntity<Void>  notifyOrderIsReadyEntity(@Validated @RequestBody OrderIsReadyRequest orderIsReadyRequest){
        orderHandler.notifyOrderIsReady(orderIsReadyRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Indicate that the order of a client was Delivered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client got his order", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/setOrderToDelivered")
    public ResponseEntity<Void>  orderWasDeliveredEntity(@Validated @RequestBody OrderDeveliveredRequest orderDeliveredRequest){
        orderHandler.setOrderToDelivered(orderDeliveredRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Indicate that the order of a client was Delivered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client got his order", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/cancelOrder")
    public ResponseEntity<Void>  orderToBeCanceledEntity(@Validated @RequestBody OrderToBeCanceledRequest orderToBeCanceledRequest){
        orderHandler.setOrderToCanceled(orderToBeCanceledRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
