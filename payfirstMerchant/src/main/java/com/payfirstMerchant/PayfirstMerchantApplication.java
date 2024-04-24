package com.payfirstMerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class PayfirstMerchantApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayfirstMerchantApplication.class, args);
	}

}
