package com.streammusic.strawberryfields;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StrawberryfieldsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrawberryfieldsApplication.class, args);
	}

}
