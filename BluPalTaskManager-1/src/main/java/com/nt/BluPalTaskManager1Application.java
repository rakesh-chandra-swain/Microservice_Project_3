package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
public class BluPalTaskManager1Application {

	public static void main(String[] args) {
		SpringApplication.run(BluPalTaskManager1Application.class, args);
	}

}
