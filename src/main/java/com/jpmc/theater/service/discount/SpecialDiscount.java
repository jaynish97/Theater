package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpecialDiscount implements  DiscountIntf {

    @Value("${special.code.discount.amount}")
    private double discountAmount;
    @Override
    public double getDiscountedPrice(Movie movie, int specialCode) {


        if (movie.getSpecialCode() == specialCode){
            return movie.getTicketPrice() - movie.getTicketPrice() * discountAmount;
        }
        return movie.getTicketPrice();
    }
}
