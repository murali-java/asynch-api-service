package com.asynch.asynchapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AsynchApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsynchApiServiceApplication.class, args);
	}

}
