package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FaceRecognitionDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceRecognitionDiscoveryApplication.class, args);
	}

}
