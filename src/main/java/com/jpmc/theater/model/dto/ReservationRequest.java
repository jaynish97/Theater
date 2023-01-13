package com.jpmc.theater.model.dto;

import com.jpmc.theater.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private Customer customer;
    private int sequence;
    private int numberOfTickets;

}
