package com.java.OS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main Entry point class of our project
  
 * We have used CommandLineRunner to execute any code after the start of spring boot application.

 */

@SpringBootApplication
public class OrderSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
