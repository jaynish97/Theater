package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SequenceDiscountTest {

    Movie movie;

    @Autowired
    SequenceDiscount sequenceDiscount;

    @BeforeEach
    void setUp(){
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    }
    @Test
    void getDiscountedPriceTest_SequenceEquals1(){

        double price = sequenceDiscount.getDiscountedPrice(movie,1);

        assertEquals(movie.getTicketPrice()-3,price);
    }

    @Test
    void getDiscountedPriceTest_SequenceEquals2(){

        double price = sequenceDiscount.getDiscountedPrice(movie,2);

        assertEquals(movie.getTicketPrice()-2,price);
    }

    @Test
    void getDiscountedPriceTest_SequenceEquals7(){

        double price = sequenceDiscount.getDiscountedPrice(movie,7);

        assertEquals(movie.getTicketPrice()-1,price);
    }

    @Test
    void getDiscountedPriceTest_SequenceHasNoDiscount(){

        double price = sequenceDiscount.getDiscountedPrice(movie,6);

        assertEquals(movie.getTicketPrice(),price);
    }

}
