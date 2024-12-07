package com.wladi.githubgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubGatewayApplication.class, args);
	}

}
