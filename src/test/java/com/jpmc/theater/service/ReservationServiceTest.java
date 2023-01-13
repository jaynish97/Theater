package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservationServiceTest {

    Movie movie;
    Showing showing;
    @BeforeEach
    void setUp(){
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        showing =  new Showing(movie, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
    }

    @MockBean
    MovieService movieService;

    @Autowired
    ReservationService reservationService;


    @Test
    void calculateTicketPriceTest_audienceCount1(){

        int audienceCount = 1;
        Mockito.when(movieService.calculateTicketPrice(showing)).thenReturn(movie.getTicketPrice());
        double price = reservationService.getTotalFee(showing,audienceCount);

        assertEquals(movie.getTicketPrice(),price);

    }

    @Test
    void calculateTicketPriceTest_audienceCountGreaterThan1(){

        int audienceCount = 3;
        Mockito.when(movieService.calculateTicketPrice(showing)).thenReturn(movie.getTicketPrice());
        double price = reservationService.getTotalFee(showing,audienceCount);

        assertEquals(movie.getTicketPrice()*3,price);

    }

}
