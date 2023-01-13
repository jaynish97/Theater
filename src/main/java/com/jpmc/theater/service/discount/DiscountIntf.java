package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;

public interface DiscountIntf {

    double getDiscountedPrice(Movie movie, int discountKey);
}
