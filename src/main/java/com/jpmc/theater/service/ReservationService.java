package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    MovieService movieService;
    /*
        Method to calculate total cost given movie ticket price and number of tickets
     */
    public double getTotalFee(Showing showing, int numberOfTickets) {

        double cost = numberOfTickets * movieService.calculateTicketPrice(showing);

        return cost;

    }
}
