package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TimeDiscountTest {

    Movie movie;

    @Autowired
    TimeDiscount timeDiscount;

    @BeforeEach
    void setUp(){
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    }

    @Test
    void getDiscountedPriceTest_discounted_TimeAtLowerBound(){

        double price = timeDiscount.getDiscountedPrice(movie,11);

        assertEquals(movie.getTicketPrice() - movie.getTicketPrice() * .25,price);
    }

    @Test
    void getDiscountedPriceTest_discounted_TimeAtUpperBound(){

        double price = timeDiscount.getDiscountedPrice(movie,16);

        assertEquals(movie.getTicketPrice() - movie.getTicketPrice() * .25,price);
    }

    @Test
    void getDiscountedPriceTest_discounted_TimeBetweenBounds(){

        double price = timeDiscount.getDiscountedPrice(movie,13);

        assertEquals(movie.getTicketPrice() - movie.getTicketPrice() * .25,price);
    }

    @Test
    void getDiscountedPriceTest_discounted_TimeOutsideBounds(){

        double price = timeDiscount.getDiscountedPrice(movie,10);

        assertEquals(movie.getTicketPrice(),price);
    }



}
