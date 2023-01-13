package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.SequenceDiscount;
import com.jpmc.theater.service.discount.SpecialDiscount;
import com.jpmc.theater.service.discount.TimeDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    SequenceDiscount sequenceDiscountService;

    @Autowired
    SpecialDiscount specialDiscountService;

    @Autowired
    TimeDiscount timeDiscountService;

    @Value("${special.discount.code}")
    private int specialCode;

    /*
    Method Returns the minimum price after all discounts are taken into consideration
     */
    public double calculateTicketPrice(Showing showing){

        Movie movie = showing.getMovie();
        double price = movie.getTicketPrice();
        price = Math.min(price,sequenceDiscountService.getDiscountedPrice(movie,showing.getSequenceOfTheDay()));
        price = Math.min(price,specialDiscountService.getDiscountedPrice(movie,specialCode));
        price = Math.min(price,timeDiscountService.getDiscountedPrice(movie,showing.getShowStartTime().getHour()+1));

        return price;
    }
}
