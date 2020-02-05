package com.netapp.security.vm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VmApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmApplication.class, args);
	}

}
