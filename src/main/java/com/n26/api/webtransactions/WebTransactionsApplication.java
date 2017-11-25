package com.n26.api.webtransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebTransactionsApplication.class, args);
	}
}
