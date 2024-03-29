package com.powerup.square.infraestructure.configuration.userclient;

import com.powerup.square.application.dto.employee.EmployeeRequest;
import com.powerup.square.application.dto.employee.EmployeeResponse;
import com.powerup.square.application.dto.user.UserRequest;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.dto.user.securityDto.AuthenticationRequest;
import com.powerup.square.application.dto.user.securityDto.AuthenticationResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="user-service",url = "http://localhost:8091/")
public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/auth/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "user/proprietary")
    public ResponseEntity<UserRequest> saveUserEntityProprietary(@Validated @RequestBody UserRequest userRequest,
                                                                 @RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @RequestMapping(method = RequestMethod.POST, value = "user/employee")
    public ResponseEntity<UserResponse> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest,
                                                                   @RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @RequestMapping(method = RequestMethod.POST, value = "user/client")
    public ResponseEntity<UserResponse> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest);

    @RequestMapping(method = RequestMethod.POST, value = "user/email/{email}")
    UserResponse getUserByEmail(@PathVariable String email);

}
