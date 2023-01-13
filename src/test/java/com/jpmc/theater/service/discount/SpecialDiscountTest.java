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
public class SpecialDiscountTest {

    Movie movie;

    @Autowired
    SpecialDiscount specialDiscount;

    @BeforeEach
    void setUp(){
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    }


    @Test
    void getDiscountedPriceTest_SpecialDiscountCodeValid(){

        double price = specialDiscount.getDiscountedPrice(movie,1);

        assertEquals(movie.getTicketPrice() - movie.getTicketPrice() * .20,price);
    }

    @Test
    void getDiscountedPriceTest_SpecialDiscountCodeNotValid(){

        movie.setSpecialCode(0);
        double price = specialDiscount.getDiscountedPrice(movie,1);
        assertEquals(movie.getTicketPrice(),price);
    }
}
