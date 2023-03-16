package com.powerup.square;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PowerUpSquareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerUpSquareApplication.class, args);
	}

}
