package com.jpmc.theater;

import com.jpmc.theater.model.Theater;
import com.jpmc.theater.service.TheaterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class MovieTheaterApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieTheaterApplication.class, args);
	}

}
