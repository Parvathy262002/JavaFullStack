package com.springrest.SpringRestAPI;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@ComponentScan({ "com" })
//@EnableJpaRepositories(basePackages = "com.springrest.SpringRestAPI.dao")
//@EntityScan({ "com.springrest.SpringRestAPI.model" })
@EnableTransactionManagement
public class SpringRestAPIApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringRestAPIApplication.class, args);
	}

}
