package com.powerup.square.domain.exceptionHandler;

import com.powerup.square.domain.exception.*;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class ControllerAdvisor {

    private static final String MESSAGE = "message";
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(PlateAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            PlateAlreadyExistsException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "Plate already exists"));
    }

    @ExceptionHandler(PlateNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            PlateNotFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "Plate not found"));
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            RestaurantAlreadyExistsException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "Restaurant already exists"));
    }

    @ExceptionHandler(RestaurantDoNotExistException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            RestaurantDoNotExistException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "Restaurant do not exists"));
    }

    @ExceptionHandler(SameStateException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            SameStateException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "Plate has already the state asked"));
    }

    @ExceptionHandler(PendingOrderAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            PendingOrderAlreadyExistsException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "You've already a pending order, please wait until its done to order more :)"));
    }

    @ExceptionHandler(PlateIsNotFromThisRestaurantException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            PlateIsNotFromThisRestaurantException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "A plate may not be from the restaurant you asked"));
    }

    @ExceptionHandler(OrderDoNotExistsException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            OrderDoNotExistsException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "Order do not exists for this client, please check the orders list"));
    }

    @ExceptionHandler(OrderIsNotReadyException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            OrderIsNotReadyException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "An Order that is not ready can't be changed to delivered"));
    }

    @ExceptionHandler(OrderPinGivenIncorrectException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            OrderPinGivenIncorrectException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "The PIN given is not the correct one, please check again"));
    }

    @ExceptionHandler(OrderStateDeliveredException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            OrderStateDeliveredException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "The flow of this order was finished and the state shouldn't be changed"));
    }

    @ExceptionHandler(OrderIsNotPendingException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            OrderIsNotPendingException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "The order can't be canceled, for more details please check the SMS that will arrive to your phone"));
    }

    @ExceptionHandler(OrderAssignedAlreadyException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            OrderAssignedAlreadyException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "The order was assigned to another employee, please take another order(s)"));
    }
}