package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SequenceDiscount implements  DiscountIntf{

    @Value("#{${sequenceDiscount}}")
    private Map<Integer,Integer> sequenceDiscount;
    @Override
    public double getDiscountedPrice(Movie movie, int showSequence) {

        return movie.getTicketPrice() - sequenceDiscount.getOrDefault(showSequence,0);
    }
}
