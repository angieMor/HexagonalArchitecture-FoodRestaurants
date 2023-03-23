package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.employee.EmployeeRequest;
import com.powerup.square.application.dto.employee.EmployeeResponse;
import com.powerup.square.application.dto.user.UserRequest;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.dto.user.securityDto.AuthenticationRequest;
import com.powerup.square.application.dto.user.securityDto.AuthenticationResponse;
import com.powerup.square.application.handler.impl.EmployeeHandler;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserClient userClient;

    private final EmployeeHandler employeeHandler;

    @Operation(summary = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userClient.authenticate(request).getBody());
    }

    @Operation(summary = "Add a new proprietary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/proprietary")
    public ResponseEntity<UserRequest> saveUserEntityProprietary(@Validated @RequestBody UserRequest userRequest,
                                                                 @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityProprietary(userRequest, token).getBody());
    }


    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/employee")
    public ResponseEntity<UserResponse> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest,
                                                                   @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        UserResponse userResponse = userClient.saveUserEntityEmployee(userRequest, token).getBody();

        // Getting info from token
        String token1 = token.replace("Bearer ", "");

        // Split into 3 parts with . delimiter
        String[] parts = token1.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(parts[1]));

        //Accessing to the Json String info
        JSONObject jsonObject = new JSONObject(payload);
        String proprietaryEmail = (String) jsonObject.get("sub");

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setIdUser(userResponse.getId());
        employeeRequest.setField("Employee");
        employeeRequest.setIdRestaurant(userClient.getUserByEmail(proprietaryEmail).getId());

        employeeHandler.saveEmployee(employeeRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<UserResponse> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityClient(userRequest).getBody());
    }

    @Operation(summary = "get User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content),
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
