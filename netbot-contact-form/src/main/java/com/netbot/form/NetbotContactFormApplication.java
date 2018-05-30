package com.netbot.form;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class NetbotContactFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetbotContactFormApplication.class, args);
	}
}
