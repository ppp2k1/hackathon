package com.netapp.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GraphQLGateway {

	public static void main(String[] args) {
		SpringApplication.run(GraphQLGateway.class, args);
	}
}
