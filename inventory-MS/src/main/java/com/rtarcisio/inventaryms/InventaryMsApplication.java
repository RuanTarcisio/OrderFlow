package com.rtarcisio.inventaryms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
public class InventaryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaryMsApplication.class, args);
	}

}
