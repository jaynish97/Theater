package com.jpmc.theater.controller;

import com.jpmc.theater.exception.BadRequestException;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.model.dto.ReservationRequest;
import com.jpmc.theater.model.dto.ScheduleResponse;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @Autowired
    ReservationService reservationService;

    /*
    Method To allow customer to create reservations
    */
    @PostMapping(path = "/theater/reserve", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Double createReservation( @RequestBody ReservationRequest reservationRequest){

        int sequence = reservationRequest.getSequence();

        if (!Theater.schedule.containsKey(sequence)){
            throw new BadRequestException("Invalid Sequence Number: Minimum: 1 and Maximium: " + Theater.schedule.size());
        }

        if (reservationRequest.getNumberOfTickets() < 1){
            throw new BadRequestException("Amount of Tickets Being Purchased Must be Greater than one");
        }

       return reservationService.getTotalFee(Theater.schedule.get(sequence),reservationRequest.getNumberOfTickets());
    }

    /*
    Method To allow customer to view movie schedules in json format
    */
    @GetMapping(path = "/theater/schedule/text")
    public String getScheduleAsText(){

        return theaterService.getMovieScheduleText();
    }

    /*
    Method To allow customer to view movie schedules in simple text format
    */
    @GetMapping(path = "/theater/schedule/json")
    public ScheduleResponse[] getScheduleAsJson(){


        return theaterService.getMovieSchedule();
    }

}
