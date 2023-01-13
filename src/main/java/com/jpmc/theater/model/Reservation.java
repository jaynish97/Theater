package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {

    private Customer customer;
    private Showing showing;
    private int audienceCount;

}
