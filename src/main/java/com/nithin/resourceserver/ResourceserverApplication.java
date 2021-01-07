package com.nithin.resourceserver;

import com.nithin.resourceserver.security.JwtTokenCreater;
import com.nithin.resourceserver.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResourceserverApplication {

	public static void main(String[] args) {

		JwtTokenCreater creater = new JwtTokenCreater();
		creater.createJwtSigned();
		SpringApplication.run(ResourceserverApplication.class, args);
	}

}
