package com.crmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EntityScan({ "com.crmbackend" })

public class CRMBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(CRMBackEndApplication.class, args);
	}
}
