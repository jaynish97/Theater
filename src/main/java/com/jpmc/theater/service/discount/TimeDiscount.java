package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TimeDiscount implements  DiscountIntf {

    @Value("${theater.time.discount.lowerBound}")
    private int lowerBound;

    @Value("${theater.time.discount.upperBound}")
    private int upperBound;

    @Value("${theater.time.discount.amount}")
    private double discountAmount;

    @Override
    public double getDiscountedPrice(Movie movie, int startTimeHour) {

        if (startTimeHour >= lowerBound && startTimeHour <= upperBound){
            return movie.getTicketPrice() - movie.getTicketPrice() * discountAmount;
        }
        return movie.getTicketPrice();
    }
}
