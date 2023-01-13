package com.jpmc.theater.model;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Movie {

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;
}
