package com.kn.eshipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kn.eshipping.config.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class KnPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnPocApplication.class, args);
	}

}
