package com.eros.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eros.demo.common.utils.Configuration;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Configuration.loadProperties("./config/application.properties");
		SpringApplication application = new SpringApplication(Application.class);
		application.setDefaultProperties(Configuration.getProperties());
		application.run(args);
	}
}
