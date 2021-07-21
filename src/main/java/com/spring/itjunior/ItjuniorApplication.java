package com.spring.itjunior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ItjuniorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItjuniorApplication.class, args);
	}

}
