package com.spesa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = "com.spesa.models")
@SpringBootApplication
public class SpesaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpesaApplication.class, args);
	}

}
