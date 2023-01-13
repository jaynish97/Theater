package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.SequenceDiscount;
import com.jpmc.theater.service.discount.SpecialDiscount;
import com.jpmc.theater.service.discount.TimeDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class MovieServiceTest {

    @MockBean
    SequenceDiscount sequenceDiscount;

    @MockBean
    SpecialDiscount specialDiscount;

    @MockBean
    TimeDiscount timeDiscount;

    @Autowired
    MovieService movieService;

    Movie movie;
    Showing showing;
    @BeforeEach
    void setUp(){
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        showing =  new Showing(movie, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
    }

    @Test
    void calculateTicketPriceTest_sequenceDiscountGivesBestDiscount(){
        Mockito.when(specialDiscount.getDiscountedPrice(movie,1)).thenReturn(9.0);
        Mockito.when(timeDiscount.getDiscountedPrice(movie,showing.getShowStartTime().getHour()+1)).thenReturn(12.5);
        Mockito.when(sequenceDiscount.getDiscountedPrice(movie,showing.getSequenceOfTheDay())).thenReturn(12.5);

        double price = movieService.calculateTicketPrice(showing);

        assertEquals(9.0,price);

    }

    @Test
    void calculateTicketPriceTest_timeDiscountGivesBestDiscount(){
        Mockito.when(specialDiscount.getDiscountedPrice(movie,1)).thenReturn(9.0);
        Mockito.when(timeDiscount.getDiscountedPrice(movie,showing.getShowStartTime().getHour()+1)).thenReturn(8.5);
        Mockito.when(sequenceDiscount.getDiscountedPrice(movie,showing.getSequenceOfTheDay())).thenReturn(12.5);

        double price = movieService.calculateTicketPrice(showing);

        assertEquals(8.5,price);

    }

    @Test
    void calculateTicketPriceTest_specialDiscountGivesBestDiscount(){
        Mockito.when(specialDiscount.getDiscountedPrice(movie,1)).thenReturn(9.0);
        Mockito.when(timeDiscount.getDiscountedPrice(movie,showing.getShowStartTime().getHour()+1)).thenReturn(8.5);
        Mockito.when(sequenceDiscount.getDiscountedPrice(movie,showing.getSequenceOfTheDay())).thenReturn(8.0);

        double price = movieService.calculateTicketPrice(showing);

        assertEquals(8.0,price);

    }

    @Test
    void calculateTicketPriceTest_noDiscount(){
        Mockito.when(specialDiscount.getDiscountedPrice(movie,1)).thenReturn(12.5);
        Mockito.when(timeDiscount.getDiscountedPrice(movie,showing.getShowStartTime().getHour()+1)).thenReturn(12.5);
        Mockito.when(sequenceDiscount.getDiscountedPrice(movie,showing.getSequenceOfTheDay())).thenReturn(12.5);

        double price = movieService.calculateTicketPrice(showing);

        assertEquals(showing.getMovie().getTicketPrice(),price);

    }
}
