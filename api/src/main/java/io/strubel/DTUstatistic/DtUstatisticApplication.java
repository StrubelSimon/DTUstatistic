package io.strubel.DTUstatistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DtUstatisticApplication {

	public static void main(String[] args) {
		SpringApplication.run(DtUstatisticApplication.class, args);
	}

}
