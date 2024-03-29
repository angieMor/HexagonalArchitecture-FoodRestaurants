package com.powerup.square.infraestructure.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()

                // Allows to visualize Swagger without authentication
                .antMatchers( "/api/v1/auth/**","/api/user/authenticate**","/swagger-ui/**", "/swagger-resources/**",
                        "/v2/api-docs/**", "/v3/api-docs/**")
                .permitAll()

                // Validating permits to access the endpoints

                // User Feign microservice
                .antMatchers("/api/user/proprietary").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/user/employee").hasAnyAuthority("ROLE_ADMIN","ROLE_PROPRIETARY")
                .antMatchers("/api/user/client**").permitAll()

                // Food restaurants microservices

                //Restaurant Security
                .antMatchers("/restaurants/createRestaurant").hasAnyAuthority("ROLE_ADMIN","ROLE_PROPRIETARY")
                .antMatchers("/restaurants/allRestaurants**").permitAll()
                //Plates security
                .antMatchers("/plates/createPlate").hasAnyAuthority("ROLE_PROPRIETARY")
                .antMatchers("/plates/putPlate").hasAnyAuthority("ROLE_PROPRIETARY")
                .antMatchers("/plates/putActivate").hasAnyAuthority("ROLE_PROPRIETARY")
                .antMatchers("/plates/allPlates").permitAll()
                //Orders security
                .antMatchers("/orders/createOrder").permitAll()
                .antMatchers("/orders/ordersByState").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers("/orders/asignOrder").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers("/orders/notifyOrderIsReady").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers("/orders/setOrderToDelivered").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers("/orders/cancelOrder").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}