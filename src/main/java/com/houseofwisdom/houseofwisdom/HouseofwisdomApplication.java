package com.houseofwisdom.houseofwisdom;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class HouseofwisdomApplication {

	private static final Logger logger = LoggerFactory.getLogger(HouseofwisdomApplication.class);

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(HouseofwisdomApplication.class, args);
	}

}
