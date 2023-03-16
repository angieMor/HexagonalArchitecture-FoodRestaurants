package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.EmployeeResponse;
import com.powerup.square.application.dto.user.UserRequest;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.dto.user.securityDto.AuthenticationRequest;
import com.powerup.square.application.dto.user.securityDto.AuthenticationResponse;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserClient userClient;

    @Operation(summary = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userClient.authenticate(request).getBody());
    }

    @Operation(summary = "Add a new proprietary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/proprietary")
    public ResponseEntity<UserRequest> saveUserEntityProprietary(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityProprietary(userRequest).getBody());
    }


    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponse> saveUserEntityEmployee(@Validated @RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityEmployee(employeeRequest).getBody());
    }

    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<UserResponse> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityClient(userRequest).getBody());
    }

    @Operation(summary = "get User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(userClient.getUserByEmail(email));
    }

    public static String userLoginApplication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }


}
