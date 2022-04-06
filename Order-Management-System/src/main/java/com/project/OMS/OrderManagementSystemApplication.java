package com.project.OMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * Main Entry point class of our project
  
 * We have used CommandLineRunner to execute any code after the start of spring boot application.

 */
@SpringBootApplication
public class OrderManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
